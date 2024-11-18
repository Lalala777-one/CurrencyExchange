package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.Map;

public interface ExchangeRateRepo {
    void addExchangeRate(String fromCurrency, String toCurrency, double rate);
    void updateExchangeRate(String fromCurrency, String toCurrency, double rate);
    double getExchangeRate(String fromCurrency, String toCurrency);
    void removeExchangeRate(String fromCurrency, String toCurrency);
    Map<Currency, ExchangeRate> getAllExchangeMap();
}
