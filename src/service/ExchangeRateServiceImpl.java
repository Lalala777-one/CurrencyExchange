package service;

import exceptionsUtils.ExchangeRateException;
import model.Currency;
import repository.ExchangeRateRepo;

public class ExchangeRateServiceImpl implements ExchangeRateService {
    private static final Currency BASE_CURRENCY = new Currency("Euro", "EUR"); // Базовая валюта - евро
    private final ExchangeRateRepo exchangeRateRepo;

    public ExchangeRateServiceImpl(ExchangeRateRepo exchangeRateRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
    }

    @Override
    public double convertCurrency(Currency fromCurrency, Currency toCurrency, double amount)
            throws ExchangeRateException.CurrencyNotFoundException, ExchangeRateException.ExchangeRateNotFoundException {

        if (fromCurrency == null || toCurrency == null) {
            throw new ExchangeRateException.CurrencyNotFoundException(fromCurrency == null ? "Unknown" : fromCurrency.getCode());
        }

        // Проверка существования курса обмена в репозитории
        double exchangeRate = exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        if (exchangeRate == 0) {
            throw new ExchangeRateException.ExchangeRateNotFoundException(fromCurrency.getCode(), toCurrency.getCode());
        }

        // Если одна из валют - это базовая валюта, можно сразу конвертировать
        if (fromCurrency.getCode().equals(BASE_CURRENCY.getCode())) {
            return amount * exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        } else if (toCurrency.getCode().equals(BASE_CURRENCY.getCode())) {
            return amount / exchangeRateRepo.getExchangeRate(toCurrency, fromCurrency);
        } else {
            // Перевести сначала в евро, а потом в целевую валюту
            double amountInBaseCurrency = amount / exchangeRateRepo.getExchangeRate(fromCurrency, BASE_CURRENCY); // Конвертируем в евро
            return amountInBaseCurrency * exchangeRateRepo.getExchangeRate(BASE_CURRENCY, toCurrency); // Конвертируем из евро в целевую валюту
        }
    }

    @Override
    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode)
            throws ExchangeRateException.CurrencyNotFoundException, ExchangeRateException.ExchangeRateNotFoundException {

        Currency fromCurrency = new Currency("Unknown", fromCurrencyCode);
        Currency toCurrency = new Currency("Unknown", toCurrencyCode);

        if (fromCurrency == null || toCurrency == null) {
            throw new ExchangeRateException.CurrencyNotFoundException(fromCurrencyCode);
        }

        double exchangeRate = exchangeRateRepo.getExchangeRate(fromCurrency, toCurrency);
        if (exchangeRate == 0) {
            throw new ExchangeRateException.ExchangeRateNotFoundException(fromCurrencyCode, toCurrencyCode);
        }

        return exchangeRate;
    }

    @Override
    public void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate)
            throws ExchangeRateException.CurrencyNotFoundException {

        Currency fromCurrency = new Currency("Unknown", fromCurrencyCode);
        Currency toCurrency = new Currency("Unknown", toCurrencyCode);

        if (fromCurrency == null || toCurrency == null) {
            throw new ExchangeRateException.CurrencyNotFoundException(fromCurrencyCode);
        }

        exchangeRateRepo.updateExchangeRate(fromCurrency, toCurrency, newRate);
    }
}