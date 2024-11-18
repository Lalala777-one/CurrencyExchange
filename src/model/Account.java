package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger accountIdGenerator = new AtomicInteger(1);
    private int id;
    private double balance;
    private final Currency currency;
    private final int userId;

        public Account(Currency currency, int userId) { // может быть только один Account в конкретной валюте
        this.id = accountIdGenerator.getAndIncrement();
        this.balance = 0.0;
        this.currency = currency;
        this.userId = userId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getUserId() {
        return userId;
    }
}
