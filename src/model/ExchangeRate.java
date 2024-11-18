package model;

import java.time.LocalDateTime;

public class ExchangeRate {

    private final double rate;
    private LocalDateTime date;

    // private Currency fromCurrency;  // Исходная валюта
    // private Currency toCurrency;

    public ExchangeRate(double rate) {
        this.rate = rate;
        this.date = LocalDateTime.now();
    }

    public double getRate() {
        return rate;
    }
}
