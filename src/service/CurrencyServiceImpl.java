package service;

import exceptionsUtils.CurrencyException;
import model.Currency;
import repository.CurrencyRepo;

import java.util.Arrays;
import java.util.List;

public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepo currencyRepo;

    public CurrencyServiceImpl(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
        initializeCurrencies();
    }

    private void initializeCurrencies() {
        List<Currency> defaultCurrencies = Arrays.asList(
            new Currency("USD", "Доллар США"),
            new Currency("EUR", "Евро"),
            new Currency("JPY", "Японская иена"),
            new Currency("CZK", "Чешская крона"),
            new Currency("CNY", "Китайский юань"),
            new Currency("AUD", "Австралийский доллар")
    );

        // Добавляем валюты в репозиторий, если они еще не добавлены
        for (Currency currency : defaultCurrencies) {
            // Проверяем, чтобы валюта с таким кодом еще не была добавлена
            if (currencyRepo.getCurrencyByCode(currency.getCode()) == null) {
                currencyRepo.addCurrency(currency);
            }
        }
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
       return currencyRepo.getAllCurrencies();
    }

    @Override
    public void removeCurrency(String currencyCode) {
        // Проверяем, существует ли валюта с указанным кодом
        Currency currency = currencyRepo.getCurrencyByCode(currencyCode);
        if (currency == null) {
            throw new IllegalArgumentException("Валюта с кодом " + currencyCode + " не найдена.");
        }

        // Удаляем валюту из репозитория
        boolean removed = currencyRepo.deleteCurrency(currencyCode);
        if (!removed) {
            throw new RuntimeException("Ошибка при удалении валюты с кодом " + currencyCode + ".");
        }

        System.out.println("Валюта с кодом " + currencyCode + " успешно удалена.");
    }

}
