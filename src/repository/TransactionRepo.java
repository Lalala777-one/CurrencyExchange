package repository;

import model.Transaction;

import java.util.Map;

public interface TransactionRepo {
    void saveTransaction(Transaction transaction);
    void deleteTransaction(int id, Transaction transaction);
    Transaction findTransactionById(int id);
    Map<Integer, Transaction> findAllTransactions();
}
