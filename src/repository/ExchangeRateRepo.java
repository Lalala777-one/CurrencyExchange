package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.Map;

public interface ExchangeRateRepo {
    void addExchangeRate(Currency fromCurrency, Currency toCurrency, double rate);
    void updateExchangeRate(Currency fromCurrency, Currency toCurrency, double rate);
    double getExchangeRate(Currency fromCurrency, Currency toCurrency);
    void removeExchangeRate(Currency fromCurrency, Currency toCurrency);
    Map<Currency, ExchangeRate> getAllExchangeMap();
}
