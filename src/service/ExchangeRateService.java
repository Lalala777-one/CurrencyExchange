package service;

public interface ExchangeRateService {

    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode);

    // Обновить курс валюты (доступно только админу)
    void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate);

}
