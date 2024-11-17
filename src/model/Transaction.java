package model;

import java.time.LocalDateTime;

public class Transaction {

    private final int transactionId;

    private final Account fromAccount;
    private final Account toAccount;
    private final Currency fromCurrency;
    private final Currency toCurrency;

    private final double fromAmount;
    private final double toAmount;

    private final double exchangeRate;
    private final LocalDateTime timeOperation;

    public Transaction(int transactionId,
                       Account fromAccount,
                       Account toAccount,
                       Currency fromCurrency,
                       Currency toCurrency,
                       double fromAmount,
                       double toAmount,
                       double exchangeRate) {

        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.exchangeRate = exchangeRate;
        this.timeOperation = LocalDateTime.now();

    }

    public int getTransactionId() {
        return transactionId;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public double getFromAmount() {
        return fromAmount;
    }

    public double getToAmount() {
        return toAmount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public LocalDateTime getTimeOperation() {
        return timeOperation;
    }

    // Определение userId  через счет пользователя:
    public int getFromUserId() {
        return fromAccount.getUserId();  // Получаем userId через fromAccount
    }


    // получить userId, который владеет toAccount (счетом-получателем) в транзакции
    public int getToUserId() {
        return toAccount != null ? toAccount.getUserId() : -1;  // Если нет toAccount  возвращаем -1
    } // является ли пользователь получателем средств.
}
