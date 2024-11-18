package service;

import model.Account;

import java.util.List;

public class AccountServiceImpl implements AccountService{
    @Override
    public void createAccount(int userId, String currencyCode, double initialBalance) {
    }

    @Override
    public Account getAccountById(int accountId) {
        return null;
    }

    @Override
    public List<Account> getAllAccountsForUser(int userId) {
        return List.of();
    }

    @Override
    public double checkBalance(int userId, int accountId) {
        return 0;
    }

    @Override
    public void transferMoney(int userId, int fromAccountId, int toAccountId, double amount) {
    }

    @Override
    public void closeAccount(int userId, int accountId) {
    }
}
