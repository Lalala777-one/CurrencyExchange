package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.*;

public class ExchangeRateRepoImpl implements ExchangeRateRepo {

    private Map<Currency, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();

    public ExchangeRateRepoImpl() {
        initializeExchangeRates();
    }

    private void initializeExchangeRates() {
        Currency eur = new Currency("Euro", "EUR");
        Currency usd = new Currency("US Dollar", "USD");
        Currency sek = new Currency("Swedish Krona", "SEK");
        Currency gbp = new Currency("British Pound", "GBP");
        Currency jpy = new Currency("Japanese Yen", "JPY");
        Currency chf = new Currency("Swiss Franc", "CHF");

        addExchangeRate(eur, usd, 0.92);
        addExchangeRate(eur, sek, 11.5);
        addExchangeRate(eur, gbp, 0.88);
        addExchangeRate(eur, jpy, 150.0);
        addExchangeRate(eur, chf, 0.95);
    }

    @Override
    public void addExchangeRate(Currency fromCurrency, Currency toCurrency, double rate) {
        ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
        exchangeRateMap.put(fromCurrency, exchangeRate);
    }

    @Override
    public void updateExchangeRate(Currency fromCurrency, Currency toCurrency, double rate) {
        ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
        exchangeRateMap.put(fromCurrency, exchangeRate);
    }

    @Override
    public double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        ExchangeRate exchangeRate = exchangeRateMap.get(fromCurrency);
        return exchangeRate.getRate();
    }

    @Override
    public void removeExchangeRate(Currency fromCurrency, Currency toCurrency) {
        ExchangeRate exchangeRate = exchangeRateMap.get(fromCurrency);
        exchangeRateMap.remove(fromCurrency);
    }

    @Override
    public Map<Currency, ExchangeRate> getAllExchangeMap() {
        return new LinkedHashMap<>(exchangeRateMap);
    }

}
