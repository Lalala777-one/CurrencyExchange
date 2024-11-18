package service;

import repository.CurrencyRepo;
import repository.ExchangeRateRepo;

public class ExchangeRateServiceImpl implements ExchangeRateService{

    private final ExchangeRateRepo exchangeRateRepo;
    private final CurrencyRepo currencyRepo;

    public ExchangeRateServiceImpl(ExchangeRateRepo exchangeRateRepo, CurrencyRepo currencyRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
        this.currencyRepo = currencyRepo;
    }

    @Override
    public void initializeExchangeRates() {

    }

    @Override
    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode) {
        return 0;
    }

    @Override
    public void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate) {

    }

    @Override
    public double convertCurrency(String fromCurrencyCode, String toCurrencyCode, double amount) {
        return 0;
    }
}
