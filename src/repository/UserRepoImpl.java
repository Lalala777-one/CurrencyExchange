package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo {
    private List<User> users = new ArrayList<>();

    @Override
    public User findUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveUser(User user) {
        users.add(user);
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }
}
