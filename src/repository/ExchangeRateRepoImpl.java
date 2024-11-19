package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExchangeRateRepoImpl implements ExchangeRateRepo {

    private Map<Currency, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();

   /*

     // Храним курсы обмена для каждой валюты
     private Map<String, Map<String, Double>> exchangeRates = new HashMap<>();


// Конструктор для инициализации курсов валют
    public ExchangeRateRepositoryImpl() {
        // Инициализация курсов относительно евро (EUR)
        Currency eur = new Currency("EUR", "Euro");
        Currency usd = new Currency("USD", "US Dollar");
        Currency gbp = new Currency("GBP", "British Pound");
        Currency jpy = new Currency("JPY", "Japanese Yen");

         // Устанавливаем курсы
        setRate(eur, usd, 1.1);   // 1 EUR = 1.1 USD
        setRate(eur, gbp, 0.85);  // 1 EUR = 0.85 GBP
        setRate(eur, jpy, 130.5); // 1 EUR = 130.5 JPY
    }

    // Устанавливаем курс между двумя валютами
    public void setRate(Currency fromCurrency, Currency toCurrency, double rate) {
        exchangeRates
            .computeIfAbsent(fromCurrency.getCode(), k -> new HashMap<>())
            .put(toCurrency.getCode(), rate);
    }

    // Получаем курс обмена для двух валют
    public double getRate(Currency fromCurrency, Currency toCurrency) {
        return exchangeRates
            .getOrDefault(fromCurrency.getCode(), new HashMap<>())
            .getOrDefault(toCurrency.getCode(), 0.0); // 0.0 если курс не найден
    }

     */



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

    /*
    // Получаем курс обмена относительно основной валюты
    public double getRate(Currency fromCurrency, Currency toCurrency) {
        return exchangeRates
            .getOrDefault(fromCurrency.getCode(), new HashMap<>())
            .getOrDefault(toCurrency.getCode(), 0.0);


      */

}
