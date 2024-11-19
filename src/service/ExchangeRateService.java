package service;

public interface ExchangeRateService {

    // Метод для инициализации курсов валют относительно евро

    void initializeExchangeRates();

    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode);

    // Обновить курс валюты (доступно только админу)
    void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate);

    // Конвертировать из одной валюты в другую  ??
    double convertCurrency(String fromCurrencyCode, String toCurrencyCode, double amount);
}
