package repository;

import model.Currency;

import java.util.List;

public interface CurrencyRepo {
    void addCurrency(Currency currency);
    Currency getCurrencyByCode(String currencyCode);
    List<Currency> getAllCurrencies();
}
