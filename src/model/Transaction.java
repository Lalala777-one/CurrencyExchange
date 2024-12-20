package model;

import repository.AccountRepo;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
    private static final AtomicInteger transactionIdGenerator = new AtomicInteger(1);
    private final int transactionId;
    private final Account fromAccount;
    private final Account toAccount;
    private final Currency fromCurrency;
    private final Currency toCurrency;

    private final double fromAmount;
    private final double toAmount;

    private final double exchangeRate;
    private final LocalDateTime timeOperation;

    public Transaction(Account fromAccount,
                       Account toAccount,
                       Currency fromCurrency,
                       Currency toCurrency,
                       double fromAmount,
                       double toAmount,
                       double exchangeRate) {
        this.transactionId = transactionIdGenerator.getAndIncrement();;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency != null ? toCurrency : fromCurrency;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount != 0 ? toAmount : fromAmount * exchangeRate;
        this.exchangeRate = exchangeRate;
        this.timeOperation = LocalDateTime.now();
    }

    // todo проверить вывод
    @Override
    public String toString() {
        return String.format("Транзакция {\n" +
                        "  ID: %-10d\n" +
                        "  Счёт отправителя: %-20s\n" +
                        "  Счёт получателя: %-20s\n" +
                        "  Сумма отправления: %-10.2f %s\n" +
                        "  Сумма получения: %-10.2f %s\n" +
                        "}",
                transactionId, fromAccount, toAccount, fromAmount, fromCurrency, toAmount, toCurrency);
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
