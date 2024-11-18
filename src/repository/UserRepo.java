package repository;

import model.User;

public interface UserRepo {
    User findUserByEmail(String email);
    void saveUser(User user);
    void deleteUser(User user);
}
