package repository;

import java.util.Map;

public interface ExchangeRateRepo {
    void addExchangeRate(String fromCurrency, String toCurrency, double rate);
    void updateExchangeRate(String fromCurrency, String toCurrency, double rate);
    double getExchangeRate(String fromCurrency, String toCurrency);
    void removeExchangeRate(String fromCurrency, String toCurrency);
    Map<String, Double> getAllExchangeRatesForCurrency(String currency);
}
