package service;

import exceptionsUtils.AccountException;
import model.Account;
import model.Currency;
import repository.AccountRepo;
import repository.CurrencyRepo;
import repository.UserRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountServiceImpl implements AccountService{
    private final AccountRepo accountRepo;
    private final CurrencyRepo currencyRepo;
    private final UserRepo userRepo;

    public AccountServiceImpl(AccountRepo repositoryAccount, CurrencyRepo currencyRepo, UserRepo userRepo) {
        this.accountRepo = repositoryAccount;
        this.currencyRepo = currencyRepo;
        this.userRepo = userRepo;
    }

    private Map<Integer, List<String>> accountHistoryMap = new HashMap<>();

    @Override
    public void addAccountHistory(int userId, String historyRecord) {
        accountHistoryMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(historyRecord);
    }

    @Override
    public List<String> getAccountHistory(int userId) {
        return accountHistoryMap.getOrDefault(userId, Collections.emptyList());
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss (dd.MM.yy)");
        return LocalDateTime.now().format(formatter);
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

        String timestamp = getCurrentTimestamp();
        addAccountHistory(userId, timestamp + " - Создан счет в валюте: " + currencyCode + ". ID счёта: " + newAccount.getId());
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
    public List<Account> getAllAccountsByUserId(int userId) throws AccountException{
        if (userRepo.findUserById(userId).isEmpty()) {
            throw new AccountException("Пользователь с ID " + userId + " не существует.");
        }
        return accountRepo.getAccountsByUserId(userId);
    }

        /*
        @Override
        public double checkBalance(int userId, int accountId) throws AccountException {
            Account account = getAccountById(accountId);  // Проверяем, существует ли аккаунт
            if (account == null || account.getUserId() != userId) {
                throw new AccountException("Аккаунт либо не существует, либо не принадлежит данному пользователю.");
            }
            return account.getBalance();
        }

         */

    @Override
    public double checkBalance(int userId, int accountId) throws AccountException {
        // Получаем все аккаунты пользователя
        List<Account> accounts = accountRepo.getAccountsByUserId(userId);

        // Проверяем, есть ли аккаунт с нужным ID
        Account account = accounts.stream()
                .filter(acc -> acc.getId() == accountId)  // Ищем аккаунт по accountId
                .findFirst()  // Получаем первый найденный аккаунт
                .orElseThrow(() -> new AccountException("Аккаунт с таким ID не найден для данного пользователя."));

        // Проверяем, принадлежит ли аккаунт текущему пользователю
        if (account.getUserId() != userId) {
            throw new AccountException("Аккаунт не принадлежит данному пользователю.");
        }

        // Возвращаем баланс аккаунта
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
        if (accountId <= 0) {
            throw new AccountException("Некорректный ID аккаунта. ID должен быть больше нуля.");
        }
        Account account = getAccountById(accountId);

        account.setBalance(account.getBalance() + amount);
    }

    @Override
    public void withdraw(int accountId, double amount) throws AccountException {
        if (amount <= 0) {
            throw new AccountException("Сумма снятия должна быть больше нуля.");
        }
        if (accountId <= 0) {
            throw new AccountException("Некорректный ID аккаунта. ID должен быть больше нуля.");
        }
        Account account = getAccountById(accountId);
        if (account.getBalance() < amount) {
            throw new AccountException("Недостаточно средств на счете.");
        }

        account.setBalance(account.getBalance() - amount);
    }
}