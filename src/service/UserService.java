package service;

import exceptionsUtils.EmailValidateException;
import exceptionsUtils.PasswordValidateException;
import exceptionsUtils.UserException;
import model.Account;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // отвечает за регистрацию пользователя, проверку уникальности email и пароля, управл данными пользователя


    // Регистрация нового пользователя
    boolean registerUser(String email, String password, String name) throws UserException, EmailValidateException, PasswordValidateException;

    // Поиск пользователя по ID
    Optional<User> findUserById(int userId);

    // Получение списка всех пользователей
    List<User> showAllUsers() throws UserException;

    // регистрация пользователя в системе
    boolean loginUser(String email, String password) throws UserException;

    public boolean logOutUser();

    public User getActiveUser();


}

