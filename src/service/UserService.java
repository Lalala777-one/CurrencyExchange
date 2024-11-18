package service;

import model.Account;
import model.User;

import java.util.List;

public interface UserService {
    // отвечает за регистрацию пользователя, проверку уникальности email и пароля, управл данными пользователя


    // Регистрация нового пользователя
    boolean registerUser(String email, String password);

    // Поиск пользователя по ID
    User findUserById(int userId);

    // Поиск пользователя по email
    User findUserByEmail(String email);

    // Получение списка всех пользователей
    List<User> getAllUsers();


}

