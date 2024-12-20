package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.*;

public class ExchangeRateRepoImpl implements ExchangeRateRepo {

    private Map<String, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();

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

        // Обменные курсы между евро и другими валютами
        addExchangeRate(eur, usd, 0.92);
        addExchangeRate(usd, eur, 1 / 0.92);

        addExchangeRate(eur, sek, 11.5);
        addExchangeRate(sek, eur, 1 / 11.5);

        addExchangeRate(eur, gbp, 0.88);
        addExchangeRate(gbp, eur, 1 / 0.88);

        addExchangeRate(eur, jpy, 150.0);
        addExchangeRate(jpy, eur, 1 / 150.0);

        addExchangeRate(eur, chf, 0.95);
        addExchangeRate(chf, eur, 1 / 0.95);
    }

    @Override
    public void addExchangeRate(Currency fromCurrency, Currency toCurrency, double rate) {
        String key = fromCurrency.getCode() + "-" + toCurrency.getCode();
        ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
        exchangeRateMap.put(key, exchangeRate);
    }

    @Override
    public double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        String directKey = fromCurrency.getCode() + "-" + toCurrency.getCode();
        ExchangeRate directRate = exchangeRateMap.get(directKey);

        if (directRate != null) {
            return directRate.getRate();
        }

        // В случае отсутствия прямого курса, ищем через базовую валюту
        String baseCurrencyCode = "EUR"; // Базовая валюта
        Currency baseCurrency = new Currency("Euro", baseCurrencyCode);

        // Сначала конвертируем из fromCurrency в базовую валюту
        String toBaseKey = fromCurrency.getCode() + "-" + baseCurrencyCode;
        ExchangeRate toBaseRate = exchangeRateMap.get(toBaseKey);

        if (toBaseRate == null) {
            String reverseToBaseKey = baseCurrency.getCode() + "-" + fromCurrency.getCode();
            ExchangeRate reverseToBaseRate = exchangeRateMap.get(reverseToBaseKey);

            if (reverseToBaseRate != null) {
                toBaseRate = new ExchangeRate(1 / reverseToBaseRate.getRate(), reverseToBaseRate.getToCurrency(), reverseToBaseRate.getFromCurrency());
            } else {
                throw new IllegalArgumentException("Обменный курс " + fromCurrency + " к базовой валюте не найден");
            }
        }

        // Теперь конвертируем из базовой валюты в toCurrency
        String fromBaseKey = baseCurrency.getCode() + "-" + toCurrency.getCode();
        ExchangeRate fromBaseRate = exchangeRateMap.get(fromBaseKey);

        if (fromBaseRate == null) {
            throw new IllegalArgumentException("Курс для базовой валюты к " + toCurrency + " не найден");
        }

        return toBaseRate.getRate() * fromBaseRate.getRate();
    }

    @Override
    public void updateExchangeRate(Currency fromCurrency, Currency toCurrency, double rate) {
        String key = fromCurrency.getCode() + "-" + toCurrency.getCode();
        ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
        exchangeRateMap.put(key, exchangeRate);
    }

    @Override
    public void removeExchangeRate(Currency fromCurrency, Currency toCurrency) {
        String key = fromCurrency.getCode() + "-" + toCurrency.getCode();
        exchangeRateMap.remove(key);
    }

    @Override
    public Map<String, ExchangeRate> getAllExchangeMap() {
        return new LinkedHashMap<>(exchangeRateMap);
    }

    @Override
    public void clear() {
        exchangeRateMap.clear();
    }
}

   /*
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
    public double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        String directKey = fromCurrency.getCode() + "-" + toCurrency.getCode();
        ExchangeRate directRate = exchangeRateMap.get(directKey);

        if (directRate != null) {
            return directRate.getRate();
        }

        String baseCurrencyCode = "EUR";
        Currency baseCurrency = new Currency("Euro", baseCurrencyCode);

        String toBaseKey = fromCurrency.getCode() + "-" + baseCurrencyCode;
        ExchangeRate toBaseRate = exchangeRateMap.get(toBaseKey);

        if (toBaseRate == null) {
            String reverseToBaseKey = baseCurrency.getCode() + "-" + fromCurrency.getCode();
            ExchangeRate reverseToBaseRate = exchangeRateMap.get(reverseToBaseKey);

            if (reverseToBaseRate != null) {
                toBaseRate = new ExchangeRate(1 / reverseToBaseRate.getRate(), reverseToBaseRate.getToCurrency(), reverseToBaseRate.getFromCurrency());
            } else {
                throw new IllegalArgumentException("Обменный курс " + fromCurrency + " к базовой валюте не найдено");
            }
        }

        String fromBaseKey = baseCurrency.getCode() + "-" + toCurrency.getCode();
        ExchangeRate fromBaseRate = exchangeRateMap.get(fromBaseKey);

        if (fromBaseRate == null) {
            throw new IllegalArgumentException("Курс для базовой валюты к " + toCurrency + " не найден");
        }

        return toBaseRate.getRate() * fromBaseRate.getRate();
    }

    @Override
    public void updateExchangeRate(Currency fromCurrency, Currency toCurrency, double rate) {
        ExchangeRate exchangeRate = new ExchangeRate(rate, fromCurrency, toCurrency);
        exchangeRateMap.put(fromCurrency, exchangeRate);
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



    @Override
    public void clear() {
        exchangeRateMap.clear();
    }

    */


