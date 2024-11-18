package service;

public interface ExchangeRateService {

    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode);

    // Обновить курс валюты (доступно только админу)
    void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate);

    // Конвертировать из одной валюты в другую  ??
    double convertCurrency(String fromCurrencyCode, String toCurrencyCode, double amount);
}
