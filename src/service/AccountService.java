package service;

import model.Account;

import java.util.List;

public interface AccountService { //  управление аккаунтами пользователей, созданием счетов, пополнением и снятием средств

    // Создать новый счет для пользователя
    void createAccount(int userId, String currencyCode, double initialBalance);

    // Получить аккаунт пользователя по ID счета
    Account getAccountById(String accountId);

    // Получить все счета пользователя
    List<Account> getAllAccountsForUser(int userId);

    // Проверить баланс на аккаунте
    double checkBalance(int userId, String accountId);

    // Перевести деньги с одного счета на другой
    void transferMoney(int userId, String fromAccountId, String toAccountId, double amount);

    // Закрыть счет пользователя
    void closeAccount(int userId, String accountId);

}
