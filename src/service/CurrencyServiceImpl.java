package service;

import exceptionsUtils.CurrencyException;
import model.Currency;
import repository.CurrencyRepo;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepo currencyRepo;

    public CurrencyServiceImpl(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    @Override
    public void addCurrency(Currency currency) throws CurrencyException{
        if (currency == null || currency.getCode() == null || currency.getCode().isBlank()) {
            throw new CurrencyException("Валюта или ее код не могут быть пустыми.");
        }
        if (currencyRepo.getCurrencyByCode(currency.getCode()) != null) {
            throw new CurrencyException("Валюта с кодом " + currency.getCode() + " уже существует.");
        }
        currencyRepo.addCurrency(currency);
        System.out.println("Валюта " + currency.getName() + " успешно добавлена.");
    }

    @Override
    public Currency getCurrencyByCode(String currencyCode) throws CurrencyException{
        if (currencyCode == null || currencyCode.isBlank()) {
            throw new CurrencyException("Код валюты не может быть пустым.");
        }
        Currency currency = currencyRepo.getCurrencyByCode(currencyCode);
        if (currency == null) {
            throw new CurrencyException("Валюта с кодом " + currencyCode + " не найдена.");
        }
        return currency;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return List.of();
    }
}
