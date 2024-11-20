package service;

import model.Transaction;

import java.util.List;

public interface TransactionService {
    // управляет операциями (пополнение, снятие, обмен).

    // Добавить транзакцию (пополнение, снятие, обмен)
    void addTransaction(Transaction transaction);

    // Найти все транзакции для пользователя по userId
    List<Transaction> findTransactionsByUserId(int userId);

    // Получить все транзакции для конкретного счета пользователя
    List<Transaction> findTransactionsByAccountId(int accountId);

    //  обмен валюты между двумя счетами
    void exchangeTransaction(int userId, int fromAccountId, int toAccountId, double amount);

    // Пополнение счета пользователя
    void deposit(int userId, int accountId, double amount);

    // Снятие средств с счета пользователя
    void withdraw(int userId, int accountId, double amount);

    void transfer(int fromAccountId, int toAccountId, double amount);
}


