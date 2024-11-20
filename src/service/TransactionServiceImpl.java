package service;

import exceptionsUtils.TransactionException;
import model.Account;
import model.Currency;
import model.ExchangeRate;
import model.Transaction;
import repository.AccountRepo;
import repository.ExchangeRateRepo;
import repository.TransactionRepo;
import repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final ExchangeRateRepo exchangeRateRepo;
    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    public TransactionServiceImpl(ExchangeRateRepo exchangeRateRepo, TransactionRepo transactionRepo, AccountRepo accountRepo, UserRepo userRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void addTransaction(Transaction transaction) throws TransactionException{
        if (transaction == null) {
            throw new TransactionException("Транзакция не может быть null");
        }

        if (transaction.getFromAmount() <= 0) {
            throw new TransactionException("Сумма должна быть больше нуля");
        }

        if (transaction.getFromAccount() == null || transaction.getToAccount() == null) {
            throw new TransactionException("Оба аккаунта должны быть действительными.");
        }

        Account fromAccount = accountRepo.getAccountById(transaction.getFromAccount().getId());
        Account toAccount = accountRepo.getAccountById(transaction.getToAccount().getId());

        if (!accountRepo.existsById(transaction.getFromAccount().getId())) {
            throw new TransactionException("Аккаунт отправителя не найден.");
        }

        if (!accountRepo.existsById(transaction.getToAccount().getId())) {
            throw new TransactionException("Счет получателя не был найден.");
        }

        if (transaction.getToAmount() <= 0) {
            throw new TransactionException("Сумма должна быть больше нуля");
        }

        if (transaction.getExchangeRate() <= 0) {
            throw new TransactionException("Обменный курс должен быть больше 0.");
        }

        if (transaction.getFromAccount().getBalance() < transaction.getFromAmount()) {
            throw new TransactionException("На счету отправителя недостаточно средств.");
        }


        fromAccount.setBalance(fromAccount.getBalance() - transaction.getFromAmount());
        toAccount.setBalance(toAccount.getBalance() + transaction.getToAmount());

        transactionRepo.saveTransaction(transaction);

        System.out.println("Транзакция была успешно добавлена: " + transaction);
    }

    @Override
    public List<Transaction> findTransactionsByUserId(int userId) throws TransactionException{
        if (userId <= 0) {
            throw new TransactionException("Идентификатор пользователя должен быть положительным числом.");
        }

        if (!userRepo.existsById(userId)) {
            throw new TransactionException("Пользователь с указанным идентификатором не существует.");
        }

        List<Transaction> transactions = transactionRepo.findTransactionsByUserId(userId);

        if (transactions == null || transactions.isEmpty()) {
            System.out.println("Для пользователя с ID " + userId + " транзакции не найдены.");
            return new ArrayList<>();
        }

        return transactions;
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(int accountId) throws TransactionException{
        if (accountId <= 0) {
            throw new TransactionException("Идентификатор аккаунта должен быть положительным числом.");
        }

        if (!accountRepo.existsById(accountId)) {
            throw new TransactionException("Аккаунт с указанным идентификатором не существует.");
        }

        List<Transaction> transactions = transactionRepo.findTransactionsByAccountId(accountId);

        if (transactions == null || transactions.isEmpty()) {
            System.out.println("Для аккаунта с ID " + accountId + " транзакции не найдены.");
            return new ArrayList<>();
        }

        return transactions;
    }

    // предполагает, что счета принадлежат одному и тому же пользователю и занимается конкретно обменом валют.
    @Override
    public void exchangeTransaction(int userId, int fromAccountId, int toAccountId, double amount) throws TransactionException{
        Account fromAccount = accountRepo.getAccountById(fromAccountId);
        Account toAccount = accountRepo.getAccountById(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new TransactionException("Учетные записи должны быть действительными.");
        }

        if (fromAccount.getUserId() != userId || toAccount.getUserId() != userId) {
            throw new TransactionException("Учетные записи должны принадлежать одному и тому же пользователю.");
        }

        if (fromAccount.getBalance() < amount) {
            throw new TransactionException("На счету отправителя недостаточно средств.");
        }

        Currency fromCurrency = fromAccount.getCurrency();
        Currency toCurrency = toAccount.getCurrency();

        double exchangeRate = exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        double amountToTransfer = amount * exchangeRate;

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amountToTransfer);

        Transaction transaction = new Transaction(fromAccount, toAccount, fromCurrency, toCurrency, amount, amountToTransfer, exchangeRate);
        transactionRepo.saveTransaction(transaction);

        System.out.println("Сделка по обмену была успешно завершена: " + transaction);
    }

    @Override
    public void deposit(int userId, int accountId, double amount) throws TransactionException{
        if (userId <= 0) {
            throw new TransactionException("Идентификатор пользователя должен быть положительным числом.");
        }

        if (accountId <= 0) {
            throw new TransactionException("Идентификатор счета должен быть положительным числом.");
        }

        if (!userRepo.existsById(userId)) {
            throw new TransactionException("Пользователь с указанным идентификатором не существует.");
        }

        Account account = accountRepo.getAccountById(accountId);

        if (account == null) {
            throw new TransactionException("Счет с указанным идентификатором не найден.");
        }
        if (account.getUserId() != userId) {
            throw new TransactionException("Счет с ID " + accountId + " не принадлежит пользователю с ID " + userId);
        }

        if (amount <= 0) {
            throw new TransactionException("Сумма пополнения должна быть больше нуля.");
        }

        Currency accountFromCurrency = account.getCurrency();
        Currency accountToCurrency = accountRepo.getAccountById(accountId).getCurrency();

        double exchangeRate = exchangeRateRepo.getExchangeRate(accountFromCurrency, accountToCurrency);

        double toAmount = amount * exchangeRate;

        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        Transaction depositTransaction = new Transaction(
                account,
                accountRepo.getAccountById(accountId),
                accountFromCurrency,
                accountToCurrency,
                amount,
                toAmount,
                exchangeRate
        );

        transactionRepo.saveTransaction(depositTransaction);
        System.out.println("Транзакция пополнения успешно сохранена: " + depositTransaction);

        System.out.println("Счет с ID " + accountId + " успешно пополнен на сумму " + amount + ". Новый баланс: " + newBalance);
    }

    @Override
    public void withdraw(int userId, int accountId, double amount) throws TransactionException{
        if (userId <= 0) {
            throw new TransactionException("Идентификатор пользователя должен быть положительным числом.");
        }
        if (!userRepo.existsById(userId)) {
            throw new TransactionException("Пользователь с указанным идентификатором не существует.");
        }
        if (accountId <= 0) {
            throw new TransactionException("Идентификатор счета должен быть положительным числом.");
        }
        if (!accountRepo.existsById(accountId)) {
            throw new TransactionException("Счет с указанным идентификатором не найден.");
        }

        Account account = accountRepo.getAccountById(accountId);
        if (account == null) {
            throw new TransactionException("Счет не существует.");
        }
        if (account.getUserId() != userId) {
            throw new TransactionException("Счет с ID " + accountId + " не принадлежит пользователю с ID " + userId);
        }

        if (amount <= 0) {
            throw new TransactionException("Сумма снятия должна быть больше нуля.");
        }

        if (account.getBalance() < amount) {
            throw new TransactionException("Недостаточно средств на счету.");
        }

        Currency accountFromCurrency = account.getCurrency();
        Currency accountToCurrency = accountRepo.getAccountById(accountId).getCurrency();

        double exchangeRate = exchangeRateRepo.getExchangeRate(accountFromCurrency, accountToCurrency);

        double toAmount = amount * exchangeRate;


        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);

       Transaction withdrawalTransaction = new Transaction(
                account,
                accountRepo.getAccountById(accountId),
                accountFromCurrency,
                accountToCurrency,
                amount,
                toAmount,
                exchangeRate
        );

        transactionRepo.saveTransaction(withdrawalTransaction);
        System.out.println("Транзакция пополнения успешно сохранена: " + withdrawalTransaction);

    }

    // может быть использован для любых переводов между счетами (не обязательно одним и тем же пользователем)
    @Override
    public void transfer(int fromAccountId, int toAccountId, double amount) throws TransactionException{
        Account fromAccount = accountRepo.getAccountById(fromAccountId);
        Account toAccount = accountRepo.getAccountById(toAccountId);

        if (fromAccount.getBalance() < amount) {
            throw new TransactionException("Недостаточно средств на счете.");
        }

        Currency fromCurrency = fromAccount.getCurrency();
        Currency toCurrency = toAccount.getCurrency();

        if (fromCurrency.equals(toCurrency)) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
        } else {
            double exchangeRate = exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
            double convertedAmount = amount * exchangeRate;

            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + convertedAmount);

            Transaction transferTransaction = new Transaction(
                    fromAccount,
                    toAccount,
                    fromCurrency,
                    toCurrency,
                    amount,
                    convertedAmount,
                    exchangeRate
            );
            transactionRepo.saveTransaction(transferTransaction);
        }
    }
}
