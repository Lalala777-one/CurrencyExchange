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
    //todo
    public User findUserById(int userId) {
        return null;
    }

    // Получить все счета пользователя
    //todo
    public List<Account> findAccountsByUserId(int userId) {
        return null;
    }

    // Все транзакции пользователя по ID пользователя (как отправителя и получателя)
    // todo
    public List<Transaction> findTransactionsByUserId(int userId) {
        return null;
    }


    // Добавление счета
    // todo
    public void addAccount(Account account) {

    }

    // Добавление транзакции
    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }


}
