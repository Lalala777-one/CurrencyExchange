package model;

import java.time.LocalDateTime;

public class ExchangeRate {

    private final double rate;
    private LocalDateTime date;
    private Currency fromCurrency;  // Исходная валюта
    private Currency toCurrency; //  // Валюта-цель


    public ExchangeRate(double rate, Currency fromCurrency, Currency toCurrency) {
        this.rate = rate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.date = LocalDateTime.now();
    }

    public double getRate() {
        return rate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }
}
