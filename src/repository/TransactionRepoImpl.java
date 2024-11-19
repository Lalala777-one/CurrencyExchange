package repository;

import model.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionRepoImpl implements TransactionRepo {
    private Map<Integer, Transaction> transactions = new HashMap<>(); // Integer по счету

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
    } //

    @Override
    public Map<Integer, Transaction> findAllTransactions() {
        return transactions;
    } // находить транзакции по счетам

    @Override
    public List<Transaction> findTransactionsByUserId(int userId) {
        return transactions.values().stream()
                .filter(transaction -> transaction.getFromUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(int accountId) {
        return transactions.values().stream()
                .filter(transaction -> transaction.getFromAmount() == accountId)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        transactions.clear();
    }
}