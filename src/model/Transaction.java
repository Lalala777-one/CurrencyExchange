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

}
