package service;

import exceptionsUtils.EmailValidateException;
import exceptionsUtils.PasswordValidateException;
import exceptionsUtils.UserException;
import model.Account;
import model.User;

import java.util.List;

public interface UserService {
    // отвечает за регистрацию пользователя, проверку уникальности email и пароля, управл данными пользователя


    // Регистрация нового пользователя
    boolean registerUser(String email, String password, String name) throws UserException, EmailValidateException, PasswordValidateException;

    // Поиск пользователя по ID
    User findUserById(int userId);

    // Получение списка всех пользователей
    List<User> showAllUsers();

    // регистрация пользователя в системе
    boolean loginUser(String email, String password) throws UserException;


}

