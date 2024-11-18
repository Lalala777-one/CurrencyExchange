package repository;

import model.Account;
import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepoImpl implements UserRepo{

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

    // todo
    public User deleteUser(int userId){
        return null;
    } // – удаление пользователя


}