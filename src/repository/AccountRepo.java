package repository;

import model.Account;

import java.util.List;

public interface AccountRepo {
    void addAccount(Account account);
    List<Account> getAccountsByUserId(int userId);
    Account getAccountById(int accountId);
    void deleteAccount(int userId, int accountId);

    boolean existsById(int accountId);
    Integer getAccountId(int accountId);

    void clear();
}
