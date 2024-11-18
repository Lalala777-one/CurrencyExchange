package service;

import model.Account;

import java.util.List;

public interface AccountService { //  управление аккаунтами пользователей, созданием счетов, пополнением и снятием средств

    // Создать новый счет для пользователя
    void createAccount(int userId, String currencyCode, double initialBalance);

    // Получить счет пользователя по ID счета
    Account getAccountById(int accountId);

    // Получить все счета пользователя
    List<Account> getAllAccountsForUser(int userId);

    // Проверить баланс на аккаунте
    double checkBalance(int userId, int accountId);

   void deposit(int accountId, double amount); // – пополнение счета
   void withdraw(Long accountId, double amount); // – снятие средств со счета

    // Закрыть счет пользователя
    void closeAccount(int userId, int accountId);

}
