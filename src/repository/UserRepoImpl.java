package repository;

import model.Account;
import model.Role;
import model.Transaction;
import model.User;
import view.Color;

import java.util.*;

public class UserRepoImpl implements UserRepo{

    private Map<Integer, User> users = new HashMap<>();




    public UserRepoImpl() {
        addUsers(newUsers); // Добавляем пользователей в репозиторий
    }

    List<User> newUsers = Arrays.asList(
            new User("testemail@gmail.com", "Qwertzu!23", "John"),
            new User("testemail2@gmail.com", "Asdfghjk123!", "Ben"),
            new User("testemail3@gmail.com", "Yxcvbnnm!23", "Tomas")
    );




    public void addUsers(List<User> userList){
        for (User user : userList){
            users.put(user.getId(), user);
        }
    }

    // Добавляем нового пользователя
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    // Поиск пользователя по ID

    public Optional<User> findUserById(int userId) {
        return Optional.ofNullable(users.get(userId));
    }


    public boolean deleteUser(int userId){
        return users.remove(userId) != null;

    } // – удаление пользователя

    // Метод для получения всех пользователей
    public List<User> showAllUsers() {
        return new ArrayList<>(users.values()); // Возвращаем список всех пользователей
    }

    public boolean isEmailExist(String email) {
        // Проверяем, есть ли среди пользователей тот, у кого email совпадает
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    // Метод для получения пользователя по email

    public User getUserEmail(String email) {
    return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);  // Возвращаем null, если пользователь не найден
}

    // Добавляем нового пользователя с ролью ADMIN
    public void addAdminUser(String email, String password, String name) {
        User user = new User(email, password, name);  // Создаем пользователя без роли
        user.setRole(Role.ADMIN);  // Устанавливаем роль ADMIN
        addUser(user);  // Добавляем его в репозиторий
    }


}


 /*
    // Метод для проверки существования пользователя по email
    public boolean isEmailExist(String email) {
        return users.containsKey(email);
    }
     */


// private Map<Integer, Account> accounts = new HashMap<>(); // Ключ — это уникальный идентификатор счета (account.getId()).
//Значение — это сам объект Account, который содержит все данные о счете
// private Map<Integer, Transaction> transactions = new HashMap<>(); // Ключ — это уникальный идентификатор транзакции (transaction.getId()).
// Значение — это сам объект Transaction, который содержит данные о транзакции