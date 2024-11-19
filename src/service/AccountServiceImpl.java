package service;

import exceptionsUtils.AccountException;
import model.Account;
import model.Currency;
import repository.AccountRepo;
import repository.CurrencyRepo;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final CurrencyRepo currencyRepo;

    public AccountServiceImpl(AccountRepo repositoryAccount, CurrencyRepo currencyRepo) {
        this.accountRepo = repositoryAccount;
        this.currencyRepo = currencyRepo;
    }


    @Override
    public void createAccount(int userId, String currencyCode) throws AccountException {
        List<Account> userAccounts = accountRepo.getAccountsByUserId(userId);
        if (userAccounts.stream().anyMatch(account -> account.getCurrency().getCode().equals(currencyCode))) {
            throw new AccountException("Аккаунт с такой валютой уже существует для этого пользователя");
        }

        // Проверка, существует ли валюта
        Currency currency = currencyRepo.getCurrencyByCode(currencyCode);
        if (currency == null) {
            throw new AccountException("Валюта с кодом " + currencyCode + " не существует.");
        }

        Account newAccount = new Account(currency, userId);
        accountRepo.addAccount(newAccount);
    }

    @Override
    public Account getAccountById(int accountId) throws AccountException {
        // Получаем аккаунт по ID
        Account account = accountRepo.getAccountById(accountId);
        if (account == null) {
            throw new AccountException("Аккаунт с таким ID не найден.");
        }
        return account;
    }

    @Override
    public List<Account> getAllAccountsByUserId(int userId) {
        // Возвращаем все аккаунты пользователя
        return accountRepo.getAccountsByUserId(userId);
    }
git
    @Override
    public double checkBalance(int userId, int accountId) throws AccountException {
        Account account = getAccountById(accountId);  // Проверяем, существует ли аккаунт
        if (account == null || account.getUserId() != userId) {
            throw new AccountException("Аккаунт либо не существует, либо не принадлежит данному пользователю.");
        }
        return account.getBalance();
    }

    @Override
    public void deleteAccount(int userId, int accountId) throws AccountException {
        Account account = accountRepo.getAccountById(accountId);
        if (account == null || account.getUserId() != userId) {
            throw new AccountException("Аккаунт либо не существует, либо не принадлежит данному пользователю.");
        }

        accountRepo.deleteAccount(userId, accountId);
    }

    @Override
    public void deposit(int accountId, double amount) throws AccountException {
        if (amount <= 0) {
            throw new AccountException("Сумма депозита должна быть больше нуля.");
        }

        Account account = getAccountById(accountId);

        account.setBalance(account.getBalance() + amount);
    }

    @Override
    public void withdraw(int accountId, double amount) throws AccountException {
        if (amount <= 0) {
            throw new AccountException("Сумма снятия должна быть больше нуля.");
        }

        Account account = getAccountById(accountId);
        if (account.getBalance() < amount) {
            throw new AccountException("Недостаточно средств на счете.");
        }

        account.setBalance(account.getBalance() - amount);
    }
}

