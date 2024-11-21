package repository;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public interface AccountRepo {
    void addAccount(Account account);
    List<Account> getAccountsByUserId(int userId);
    Account getAccountById(int accountId);
    void deleteAccount(int userId, int accountId);

    boolean existsById(int accountId);
    void clear();
    List<String> history = new ArrayList<>();
}
