package service;

import model.Account;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public void registerUser(String email, String password) {

    }

    @Override
    public User findUserById(int userId) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void addAccountToUser(int userId, Account account) {

    }

    @Override
    public List<Account> getAccountsByUserId(int userId) {
        return List.of();
    }

    @Override
    public void removeAccountFromUser(int userId, int accountId) {

    }
}
