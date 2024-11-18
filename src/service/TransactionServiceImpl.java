package service;

import model.Transaction;

import java.util.List;

public class TransactionServiceImpl implements TransactionService{
    @Override
    public void addTransaction(Transaction transaction) {

    }

    @Override
    public void exchangeTransaction(int userId, int fromAccountId, int toAccountId, double amount) {

    }

    @Override
    public List<Transaction> findTransactionsByUserId(int userId) {
        return List.of();
    }



    @Override
    public void deposit(int userId, int accountId, double amount) {

    }

    @Override
    public void withdraw(int userId, int accountId, double amount) {

    }

    @Override
    public List<Transaction> findTransactionsByAccountId(int accountId) {
        return List.of();
    }
}
