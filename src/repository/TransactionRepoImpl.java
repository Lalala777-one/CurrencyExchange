package repository;

import model.Transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepoImpl implements TransactionRepo {
    Map<Integer, Transaction> transactions = new HashMap<>();
    int id = 0;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactions.put(++id, transaction);
    }

    @Override
    public void deleteTransaction(int id, Transaction transaction) {
        transactions.remove(id, transaction);
    }

    @Override
    public Transaction findTransactionById(int id) {
        return transactions.get(id);
    }

    @Override
    public Map<Integer, Transaction> findAllTransactions() {
        return transactions;
    }
}
