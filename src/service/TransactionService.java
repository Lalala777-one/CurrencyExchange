package service;

import model.Transaction;

import java.util.List;

public interface TransactionService {
    // управляет операциями (пополнение, снятие, обмен).

    // Добавить транзакцию (пополнение, снятие, обмен)
    void addTransaction(Transaction transaction);

    // Найти все транзакции для пользователя по userId
    List<Transaction> findTransactionsByUserId(int userId);

    //  обмен валюты между двумя счетами
    void exchangeCurrency(int userId, int fromAccountId, int toAccountId, double amount);

    // Пополнение счета пользователя
    void deposit(int userId, int accountId, double amount);

    // Снятие средств с счета пользователя
    void withdraw(int userId, int accountId, double amount);

    // Получить все транзакции для конкретного счета пользователя
    List<Transaction> findTransactionsByAccountId(int accountId);
}
