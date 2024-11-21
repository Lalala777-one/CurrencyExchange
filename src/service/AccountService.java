package service;

import exceptionsUtils.AccountException;
import model.Account;

import java.util.List;
import java.util.Map;

public interface AccountService { //  управление аккаунтами пользователей, созданием счетов, пополнением и снятием средств

    // Создать новый счет для пользователя
    void createAccount(int userId, String currencyCode) throws AccountException;

    // Получить счет пользователя по ID счета
    Account getAccountById(int accountId) throws AccountException;

    // Получить все счета пользователя
    List<Account> getAllAccountsByUserId(int userId) throws AccountException;

    // Проверить баланс на аккаунте
    double checkBalance(int userId, int accountId) throws AccountException;

   void deposit(int accountId, double amount) throws AccountException; // – пополнение счета
   void withdraw(int accountId, double amount) throws AccountException; // – снятие средств со счета

    // Закрыть счет пользователя
    void deleteAccount(int userId, int accountId) throws AccountException;

//    Map<Integer, List<String>> accountHistoryMap();
    void addAccountHistory(int userId, String historyRecord);
    List<String> getAccountHistory(int userId);
}
