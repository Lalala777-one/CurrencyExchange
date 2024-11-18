package service;

import model.Currency;

import java.util.List;

public interface CurrencyService {  // управление курсами валют и расчетами обмена.

    void addCurrency(Currency currency); //– добавление новой валюты

    Currency getCurrencyByCode(String currencyCode); // – получение валюты по коду

    List<Currency> getAllCurrencies(); //– получение всех валют

    // Конвертировать из одной валюты в другую  ??
    double convertCurrency(String fromCurrencyCode, String toCurrencyCode, double amount);


}
