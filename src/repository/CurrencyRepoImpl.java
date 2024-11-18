package repository;

import model.Currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyRepoImpl implements CurrencyRepo {
    private Map<String, Currency> currencies = new HashMap<>();

    @Override
    public Currency findCurrencyByCode(String code) {
        return currencies.get(code);
    }

    @Override
    public void saveCurrency(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    @Override
    public void deleteCurrency(Currency currency) {
        currencies.remove(currency.getCode());
    }
}
