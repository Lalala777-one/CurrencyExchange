package repository;

import model.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepo {

    // Хранение аккаунтов по userId
    private final Map<Integer, List<Account>> mapAccounts = new HashMap<>();

    // Добавление аккаунта в репозиторий
    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        // Проверка на валидность данных аккаунта
        if (account.getUserId() <= 0) {
            throw new IllegalArgumentException("No valid User ID");
        }

        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Account balance cannot be negative");
        }

        // Проверка, если для данного пользователя еще нет аккаунтов
        if (!mapAccounts.containsKey(account.getUserId())) {
            mapAccounts.put(account.getUserId(), new ArrayList<>());
        }

        // Проверка на существование аккаунта с такой валютой у данного пользователя
        List<Account> userAccounts = mapAccounts.get(account.getUserId());
        for (Account existingAccount : userAccounts) {
            if (existingAccount.getCurrency().equals(account.getCurrency())) {
                throw new IllegalArgumentException("Account with this currency already exists for the user");
            }
        }

        // Добавление аккаунта в список аккаунтов пользователя
        userAccounts.add(account);
    }

    // Получение аккаунтов пользователя по userId
    public List<Account> getAccountsByUserId(int userId) {
        return mapAccounts.getOrDefault(userId, new ArrayList<>());
    }

    // Получение аккаунта по его ID
    public Account getAccountById(int accountId) {
        for (List<Account> accounts : mapAccounts.values()) {
            for (Account account : accounts) {
                if (account.getId() == accountId) {
                    return account;
                }
            }
        }
        return null;
    }

    // Обновление аккаунта


    /*
    это у нас счет юзера
    updateAccount должен поддерживать актуальную информации о балансе и состоянии счета
     */

    public void updateAccount(Account account) {
        List<Account> accounts = mapAccounts.get(account.getUserId());

        if (accounts != null) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId() == account.getId()) {
                    accounts.set(i, account);
                    return;
                }
            }
        }

        throw new IllegalArgumentException("Account not found for user");
    }

    // Удаление аккаунта по его ID
    public void deleteAccount(int userId, int accountId) {
        List<Account> accounts = mapAccounts.get(userId);

        if (accounts != null) {
            accounts.removeIf(account -> account.getId() == accountId);
        } else {
            throw new IllegalArgumentException("User has no accounts");
        }
    }
}
