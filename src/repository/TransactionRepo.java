package repository;

import model.Transaction;

import java.util.Map;

public interface TransactionRepo {
    void saveTransaction(Transaction transaction);
    void deleteTransaction(Long id, Transaction transaction);
    Transaction findTransactionById(Long id);
    Map<Long, Transaction> findAllTransactions();
}
