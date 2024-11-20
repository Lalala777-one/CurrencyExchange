package service;

import exceptionsUtils.ExchangeRateException;
import model.Currency;
import model.ExchangeRate;
import repository.ExchangeRateRepo;

import java.util.Map;
import java.text.DecimalFormat;


public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final Currency BASE_CURRENCY = new Currency("Euro", "EUR"); // Базовая валюта - евро
    private final ExchangeRateRepo exchangeRateRepo;

    // Конструктор для инжекции репозитория
    public ExchangeRateServiceImpl(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    @Override
    public double convertCurrency(Currency fromCurrency, Currency toCurrency, double amount) throws ExchangeRateException {

        // Проверка на null для валют
        if (fromCurrency == null || toCurrency == null) {
            throw new ExchangeRateException("Обе валюты должны быть указаны.");
        }

        // Проверка наличия курса обмена в репозитории
        double exchangeRate = exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        if (exchangeRate == 0) {
            throw new ExchangeRateException("Курс обмена для валют " + fromCurrency.getCode() + " -> " + toCurrency.getCode() + " не найден.");
        }

        double result = 0;

        // Если одна из валют - это базовая валюта, можно сразу конвертировать
        if (fromCurrency.getCode().equals(BASE_CURRENCY.getCode())) {
            result = amount * exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        } else if (toCurrency.getCode().equals(BASE_CURRENCY.getCode())) {
            result = amount / exchangeRateRepo.getExchangeRate(toCurrency, fromCurrency);
        } else {
            // Перевести сначала в евро, а потом в целевую валюту
            double amountInBaseCurrency = amount / exchangeRateRepo.getExchangeRate(fromCurrency, BASE_CURRENCY);
            result = amountInBaseCurrency * exchangeRateRepo.getExchangeRate(BASE_CURRENCY, toCurrency);
        }

        // Форматируем результат с двумя знаками после запятой
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedResult = df.format(result);
        System.out.println("Конвертированная сумма: " + formattedResult + " " + toCurrency.getCode());

        return result;
    }
/*
    @Override
    public double convertCurrency(Currency fromCurrency, Currency toCurrency, double amount) throws ExchangeRateException {

        // Проверка на null для валют
        if (fromCurrency == null || toCurrency == null) {
            throw new ExchangeRateException("Обе валюты должны быть указаны.");
        }

        // Проверка наличия курса обмена в репозитории
        double exchangeRate = exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        if (exchangeRate == 0) {
            throw new ExchangeRateException("Курс обмена для валют " + fromCurrency.getCode() + " -> " + toCurrency.getCode() + " не найден.");
        }

        // Если одна из валют - это базовая валюта, можно сразу конвертировать
        if (fromCurrency.getCode().equals(BASE_CURRENCY.getCode())) {
            return amount * exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        } else if (toCurrency.getCode().equals(BASE_CURRENCY.getCode())) {
            return amount / exchangeRateRepo.getExchangeRate(toCurrency, fromCurrency);
        } else {
            // Перевести сначала в евро, а потом в целевую валюту
            double amountInBaseCurrency = amount / exchangeRateRepo.getExchangeRate(fromCurrency, BASE_CURRENCY);
            return amountInBaseCurrency * exchangeRateRepo.getExchangeRate(BASE_CURRENCY, toCurrency);
        }
    }

 */

    @Override
    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException {

        Currency fromCurrency = new Currency("Unknown", fromCurrencyCode);
        Currency toCurrency = new Currency("Unknown", toCurrencyCode);

        // Проверка на null для валют
        if (fromCurrency == null || toCurrency == null) {
            throw new ExchangeRateException("Обе валюты должны быть указаны.");
        }

        // Получение курса
        double exchangeRate = exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        if (exchangeRate == 0) {
            throw new ExchangeRateException("Курс обмена для валют " + fromCurrencyCode + " -> " + toCurrencyCode + " не найден.");
        }

        return exchangeRate;
    }

    @Override
    public void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate) throws ExchangeRateException {

        Currency fromCurrency = new Currency("Unknown", fromCurrencyCode);
        Currency toCurrency = new Currency("Unknown", toCurrencyCode);

        // Проверка на null для валют
        if (fromCurrency == null || toCurrency == null) {
            throw new ExchangeRateException("Обе валюты должны быть указаны.");
        }

        // Обновление курса
        exchangeRateRepo.updateExchangeRate(fromCurrency, toCurrency, newRate);
    }

    @Override
    public Map<Currency, ExchangeRate> getAllExchangeRates() {
        return exchangeRateRepo.getAllExchangeMap();
    }
}

