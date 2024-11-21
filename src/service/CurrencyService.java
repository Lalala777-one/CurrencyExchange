package service;

import exceptionsUtils.CurrencyException;
import model.Currency;

import java.util.List;

public interface CurrencyService {  // управление курсами валют и расчетами обмена.

    void addCurrency(Currency currency) throws CurrencyException; //– добавление новой валюты

    Currency getCurrencyByCode(String currencyCode) throws CurrencyException; // – получение валюты по коду

    List<Currency> getAllCurrencies() throws CurrencyException; //– получение всех валют

    public void removeCurrency(String currencyCode) throws CurrencyException;


    boolean isValidCurrencyCode(String currencyCode);
}
