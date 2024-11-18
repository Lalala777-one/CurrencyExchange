package repository;


import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;


public class ExchangeRateRepoImpl implements ExchangeRateRepo {

    private final Map<String, Double> exchangeRates = new HashMap<>();

    @Override
    public void addExchangeRate(String fromCurrency, String toCurrency, double rate) {
        String key = generateKey(fromCurrency, toCurrency);
        exchangeRates.put(key, rate);
    }

    private String generateKey(String fromCurrency, String toCurrency) {
        return fromCurrency + " - " + toCurrency;
    }

    @Override
    public void updateExchangeRate(String fromCurrency, String toCurrency, double rate) {
        addExchangeRate(fromCurrency, toCurrency, rate);
    }

    @Override
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        String key = generateKey(fromCurrency, toCurrency);
        return exchangeRates.get(key);
    }

    @Override
    public void removeExchangeRate(String fromCurrency, String toCurrency) {
        String key = generateKey(fromCurrency, toCurrency);
        exchangeRates.remove(key);
    }

    @Override
    public Map<String, Double> getAllExchangeRatesForCurrency(String currency) {
        Map<String, Double> ratesForCurrency = new HashMap<>();
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            if (entry.getKey().startsWith(currency + " - ")) {
                ratesForCurrency.put(entry.getKey(), entry.getValue());
            }
        }
        return ratesForCurrency;
    }
}
