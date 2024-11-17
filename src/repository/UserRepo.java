package repository;

import model.Account;
import model.Transaction;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepo {

    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Account> accounts = new HashMap<>(); // Ключ — это уникальный идентификатор счета (account.getId()).
    //Значение — это сам объект Account, который содержит все данные о счете
    private Map<Integer, Transaction> transactions = new HashMap<>(); // Ключ — это уникальный идентификатор транзакции (transaction.getId()).
   // Значение — это сам объект Transaction, который содержит данные о транзакции


    // Добавляем нового пользователя
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    // Поиск пользователя по ID
    public User findUserById(int userId) {
        return users.get(userId);
    }

    // Получить все счета пользователя
    public List<Account> findAccountsByUserId(int userId) {
        return accounts.values().stream()
                .filter(account -> account.getUserId() == userId)
                .collect(Collectors.toList());
    }

    // Все транзакции пользователя по ID пользователя (как отправителя и получателя)
    public List<Transaction> findTransactionsByUserId(int userId) {
        return transactions.values().stream()
                .filter(transaction -> transaction.getFromUserId() == userId || transaction.getToUserId() == userId)
                .collect(Collectors.toList());
    }


    // Добавление счета
    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    // Добавление транзакции
    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }


}
