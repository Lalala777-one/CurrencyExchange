package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.*;

public class ExchangeRateRepoImpl implements ExchangeRateRepo {

//    private Map<Currency, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();
//
//    @Override
//    public void addExchangeRate(Currency fromCurrency, Currency toCurrency, double rate) {
//        ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
//        exchangeRateMap.put(fromCurrency, exchangeRate);
//    }
//
//    @Override
//    public void updateExchangeRate(Currency fromCurrency, Currency toCurrency, double rate) {
//        ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
//        exchangeRateMap.put(fromCurrency, exchangeRate);
//    }
//
//    @Override
//    public double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
//        ExchangeRate exchangeRate = exchangeRateMap.get(fromCurrency);
//        return exchangeRate.getRate();
//    }
//
//    @Override
//    public void removeExchangeRate(Currency fromCurrency, Currency toCurrency) {
//        ExchangeRate exchangeRate = exchangeRateMap.get(fromCurrency);
//        exchangeRateMap.remove(fromCurrency);
//    }
//
//    @Override
//    public Map<Currency, ExchangeRate> getAllExchangeMap() {
//        return new LinkedHashMap<>(exchangeRateMap);
//    }

    private final Map<String, ExchangeRate> exchangeRates = new HashMap<>();

    // === start == ініціалізація початкових курсів
    public ExchangeRateRepoImpl() {
        // Отримуємо початкові курси валют з ExchangeRateInitializer
        Map<String, Double> initialRates = ExchangeRateInitializer.getInitialExchangeRates();

        // Додаємо кожну пару валют в репозиторій
        for (Map.Entry<String, Double> entry : initialRates.entrySet()) {
            String currencyPair = entry.getKey();  // Наприклад, "EUR-USD"
            Double rate = entry.getValue();  // Курс валют

            // Розділяємо валютну пару на дві окремі валюти
            String[] currencies = currencyPair.split("-");  // "EUR", "USD"
            Currency fromCurrency = Currency.findByCode(currencies[0]);  // Отримуємо Currency за кодом
            Currency toCurrency = Currency.findByCode(currencies[1]);  // Отримуємо Currency за кодом

            // Створюємо об'єкт ExchangeRate і додаємо його в мапу
            ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
            exchangeRates.put(currencyPair, exchangeRate);
        }
    }
    // === end ===

    @Override
    public void save(ExchangeRate exchangeRate) {
        String key = exchangeRate.getFromCurrency().getCode() + " - " + exchangeRate.getToCurrency().getCode();
        exchangeRates.put(key, exchangeRate);
    }

    @Override
    public void update(ExchangeRate exchangeRate) {
        String key = exchangeRate.getFromCurrency().getCode() + " - " + exchangeRate.getToCurrency().getCode();
        exchangeRates.put(key, exchangeRate);
    }

    @Override
    public Optional<ExchangeRate> findExchangeRateByCurrencies(String currencyFrom, String currencyTo) {
        String key = currencyFrom + " - " + currencyTo;
        return Optional.ofNullable(exchangeRates.get(key));
    }

    @Override
    public List<ExchangeRate> findAllExchangeRates() {
        return new ArrayList<>(exchangeRates.values());
    }

    // neew

    @Override
    public double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        ExchangeRate exchangeRate = exchangeRates.get(fromCurrency);
        return exchangeRate.getRate();
    }

    @Override
    public void removeExchangeRate(Currency fromCurrency, Currency toCurrency) {
        ExchangeRate exchangeRate = exchangeRates.get(fromCurrency);
        exchangeRates.remove(fromCurrency);
    }

}
