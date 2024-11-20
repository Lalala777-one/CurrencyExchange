package repository;

import model.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyRepoImpl implements CurrencyRepo {

    private Map<String, Currency> currencies = new HashMap<>();

    @Override
    public void addCurrency(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) {
        return currencies.get(currencyCode);
    } // в сервисе проверить что существует валюта с таким кодом

    @Override
    public List<Currency> getAllCurrencies() {
        return new ArrayList<>(currencies.values());
    }

    @Override
    public boolean deleteCurrency(String currencyCode) {
        if (currencies.containsKey(currencyCode)) {
            currencies.remove(currencyCode); // Удаляем валюту по её коду
            return true;
        }
        return false;
    }
}
