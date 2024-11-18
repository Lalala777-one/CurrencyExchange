package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExchangeRateRepoImpl implements ExchangeRateRepo {

    private Map<Currency, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();

    @Override
    public void addExchangeRate(String fromCurrency, String toCurrency, double rate) {

    }

    @Override
    public void updateExchangeRate(String fromCurrency, String toCurrency, double rate) {

    }

    @Override
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        return 0;
    }

    @Override
    public void removeExchangeRate(String fromCurrency, String toCurrency) {

    }

    @Override
    public Map<Currency, ExchangeRate> getAllExchangeMap() {
        return Map.of();
    }
}
