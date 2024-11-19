package model;

import java.time.LocalDateTime;

public class ExchangeRate {

    private final double rate;
    private LocalDateTime date;
    private Currency fromCurrency;  // Исходная валюта
    private Currency toCurrency; //  // Валюта-цель
    private String fromC;
    private String toC; //  // Валюта-цель


    public ExchangeRate(double rate, Currency fromCurrency, Currency toCurrency) {
        this.rate = rate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.date = LocalDateTime.now();
    }
    // new
    public ExchangeRate(double rate, String fromC, String toC) {
        this.rate = rate;
        this.fromC = fromC;
        this.toC = toC;
        this.date = LocalDateTime.now();
    } // new

    // todo проверить вывод
    @Override
    public String toString() {
        return String.format("Курс обмена: %6.2f Из валюты: '%12s'  В валюту: %12s Дата: %10s, }",
                rate,fromCurrency, toCurrency, date);
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
