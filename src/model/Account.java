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

    // todo проверить вывод
    @Override
    public String toString() {
        return String.format("ID счёта: %6d Пользователь ID: %6d Баланс: %10.2f Валюта: %6s  ",
                id, userId, balance, currency );
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
