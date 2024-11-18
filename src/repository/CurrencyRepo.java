package repository;

import model.Currency;

public interface CurrencyRepo {
    Currency findCurrencyByCode(String code);
    void saveCurrency(Currency currency);
    void deleteCurrency(Currency currency);
}
