package service;

import model.Account;
import model.User;
import repository.*;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;


    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    @Override
    public boolean registerUser(String email, String password, String name) {
        return false;
    }

    @Override
    public User findUserById(int userId) {
        return null;
    }

    @Override
    public List<User> showAllUsers() {
        return List.of();
    }
}
