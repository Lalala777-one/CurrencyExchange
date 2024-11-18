package repository;

import model.User;

public interface UserRepo {
    void addUser(User user);
    User findUserById(int userID);
    User deleteUser(int userId); // – удаление пользователя
}
