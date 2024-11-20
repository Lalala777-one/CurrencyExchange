package service;

import exceptionsUtils.ExchangeRateException;
import model.Currency;
import model.ExchangeRate;

import java.util.Map;

public interface ExchangeRateService {

    // Обновить курс валюты (доступно только админу)

     double getExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException;

    // Обновить курс валюты (доступно только админу)
    void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate) throws ExchangeRateException;

    // Конвертировать из одной валюты в другую  ??
    double convertCurrency(Currency fromCurrencyCode, Currency toCurrencyCode, double amount) throws ExchangeRateException;

    Map<String, ExchangeRate> getAllExchangeMap();
}
