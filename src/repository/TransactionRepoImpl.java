package repository;

import model.Transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepoImpl implements TransactionRepo {
    Map<Long, Transaction> transactions = new HashMap<>();
    Long id = 0L;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactions.put(++id, transaction);
    }

    @Override
    public void deleteTransaction(Long id, Transaction transaction) {
        transactions.remove(id, transaction);
    }

    @Override
    public Transaction findTransactionById(Long id) {
        return transactions.get(id);
    }

    @Override
    public Map<Long, Transaction> findAllTransactions() {
        return transactions;
    }
}
