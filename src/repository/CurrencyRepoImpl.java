package repository;

import model.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyRepoImpl implements CurrencyRepo {

    private Map<String, Currency> currencies = new HashMap<>();

    @Override
    public void addCurrency(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) {
        return currencies.get(currencyCode);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return new ArrayList<>(currencies.values());
    }
}
