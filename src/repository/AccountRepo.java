package repository;

import model.Account;

import java.util.List;

public interface AccountRepo {
    void addAccount(Account account);
    List<Account> getAccountsByUserId(int userId);
    Account getAccountById(int accountId);
    void updateAccount(Account account);
    void deleteAccount(int userId, int accountId);
}
