package service;

import model.Account;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Override
    public boolean registerUser(String email, String password) {
        return false;
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


}
