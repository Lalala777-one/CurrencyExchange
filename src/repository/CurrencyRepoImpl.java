package repository;

import model.Currency;
import view.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyRepoImpl implements CurrencyRepo {

    // ключ — это код валюты (например, "USD", "EUR"), а значение — объект Currency
    private Map<String, Currency> currencies = new HashMap<>();

    // Конструктор, в котором инициализируются доступные валюты
    public CurrencyRepoImpl() {
        initializeCurrencies();  // Инициализируем валюты
    }

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

    // Метод для инициализации валют
    private void initializeCurrencies() {
        Currency eur = new Currency("Euro", "EUR");
        Currency usd = new Currency("US Dollar", "USD");
        Currency sek = new Currency("Swedish Krona", "SEK");
        Currency gbp = new Currency("British Pound", "GBP");
        Currency jpy = new Currency("Japanese Yen", "JPY");
        Currency chf = new Currency("Swiss Franc", "CHF");

        // Добавляем валюты в мапу
        addCurrency(eur);
        addCurrency(usd);
        addCurrency(sek);
        addCurrency(gbp);
        addCurrency(jpy);
        addCurrency(chf);
    }
    @Override
    public void removeCurrency(String currencyCode) {
        if (currencies.containsKey(currencyCode)) {
            currencies.remove(currencyCode);
            System.out.println("Валюта с кодом " + currencyCode + " успешно удалена.");
        } else {
            System.out.println(Color.RED + "Ошибка: Валюта с таким кодом не найдена." + Color.RESET);
        }
    }

}