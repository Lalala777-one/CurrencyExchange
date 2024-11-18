package repository;

import model.Transaction;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepoImpl implements TransactionRepo {
    Map<Integer, Transaction> transactions = new HashMap<>();

    @Override
    public void saveTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
    }

    @Override
    public void deleteTransaction(int id) {
        transactions.remove(id);
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