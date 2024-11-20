package repository;

import model.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionRepo {
    void saveTransaction(Transaction transaction);
    void deleteTransaction(int id);
    Transaction findTransactionById(int id);
    Map<Integer, Transaction> findAllTransactions();

    List<Transaction> findTransactionsByUserId(int userId);
    List<Transaction> findTransactionsByAccountId(int accountId);

    void clear();
}
