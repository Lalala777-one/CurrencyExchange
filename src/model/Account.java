package model;

public class Account {

    private int id;
    private double balance;
    private final Currency currency;
    private final int userId;

    public Account(int id, Currency currency, int userId) { // может быть только один Accaunt  в конкретной валюте
        this.id = id;
        this.balance = 0.0;
        this.currency = currency;
        this.userId = userId;
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
