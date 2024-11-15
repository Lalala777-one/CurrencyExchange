package model;

import java.time.LocalDateTime;

public class ExchangeRate {

    private final double rate;
    private LocalDateTime date;

    public ExchangeRate(double rate) {
        this.rate = rate;
        this.date = LocalDateTime.now();
    }

    public double getRate() {
        return rate;
    }
}
