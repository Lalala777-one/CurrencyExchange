package service;

import exceptionsUtils.ExchangeRateException;
import model.Currency;

public interface ExchangeRateService {

     double getExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException.CurrencyNotFoundException, ExchangeRateException.ExchangeRateNotFoundException;

    // Обновить курс валюты (доступно только админу)
    void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate) throws ExchangeRateException.CurrencyNotFoundException;

    // Конвертировать из одной валюты в другую  ??
    double convertCurrency(Currency fromCurrencyCode, Currency toCurrencyCode, double amount) throws ExchangeRateException;
}
