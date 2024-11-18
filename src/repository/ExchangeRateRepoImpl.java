package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExchangeRateRepoImpl implements ExchangeRateRepo {

    private Map<Currency, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();

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
