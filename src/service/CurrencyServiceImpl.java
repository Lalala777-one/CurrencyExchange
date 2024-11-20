package service;

import exceptionsUtils.CurrencyException;
import model.Currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyServiceImpl implements CurrencyService{

    private final Map<String, Currency> currencyMap = new HashMap<>();

    @Override
    public void addCurrency(Currency currency) throws CurrencyException{
        if (currency == null || currency.getCode() == null || currency.getCode().isBlank()) {
            throw new CurrencyException("Валюта или ее код не могут быть пустыми.");
        }
        if (currencyMap.containsKey(currency.getCode())) {
            throw new CurrencyException("Валюта с кодом " + currency.getCode() + " уже существует.");
        }
        currencyMap.put(currency.getCode(), currency);
        System.out.println("Валюта " + currency.getName() + " успешно добавлена.");
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) throws CurrencyException{
        if (currencyCode == null || currencyCode.isBlank()) {
            throw new CurrencyException("Код валюты не может быть пустым.");
        }
        Currency currency = currencyMap.get(currencyCode);
        if (currency == null) {
            throw new CurrencyException("Валюта с кодом " + currencyCode + " найдена не найдена.");
        }
        return currency;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return List.of();
    }
}
