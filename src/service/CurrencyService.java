package service;

public interface CurrencyService {  // управление курсами валют и расчетами обмена.

    // Получить текущий курс валюты
    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode);

    // Конвертировать из одной валюты в другую
    double convertCurrency(String fromCurrencyCode, String toCurrencyCode, double amount);

    // Обновить курс валюты (доступно только админу)
    void updateExchangeRate(String fromCurrencyCode, String toCurrencyCode, double newRate);
}
/*
addCurrency(Currency currency) – добавление новой валюты
getCurrencyByCode(String currencyCode) – получение валюты по коду
getAllCurrencies() – получение всех валют

 */