package service;

import model.Account;
import model.User;

import java.util.List;

public interface UserService {
    // отвечает за регистрацию пользователя, проверку уникальности email и пароля, управл данными пользователя


    // Регистрация нового пользователя
    void registerUser(String email, String password);

    // Поиск пользователя по ID
    User findUserById(int userId);

    // Поиск пользователя по email
    User findUserByEmail(String email);

    // Получение списка всех пользователей
    List<User> getAllUsers();

    // Добавить счет пользователю
    void addAccountToUser(int userId, Account account);

    // Получить все счета пользователя
    List<Account> getAccountsByUserId(int userId);

    // Удалить счет пользователя
    void removeAccountFromUser(int userId, int accountId);
}

