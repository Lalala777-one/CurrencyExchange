package repository;

import model.User;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public interface UserRepo {


    void addUser(User user);
    Optional<User> findUserById(int userID);
    boolean deleteUser(int userId); // – удаление пользователя
    public List<User> showAllUsers();
    public boolean isEmailExist(String email);
    public User getUserEmail(String email);
    public void addAdminUser(String email, String password, String name);
}
