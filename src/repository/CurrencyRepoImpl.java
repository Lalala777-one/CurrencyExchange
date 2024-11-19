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

   /*
    // Храним валюты по их коду
    private Map<String, Currency> currencies = new HashMap<>();

    // Конструктор для инициализации валют
    public CurrencyRepository() {
        // Инициализация валют (включая основную валюту EUR)
        Currency eur = new Currency("EUR", "Euro");
        Currency usd = new Currency("USD", "US Dollar");
        Currency gbp = new Currency("GBP", "British Pound");
        Currency jpy = new Currency("JPY", "Japanese Yen");

        // Добавляем валюты в репозиторий
        addCurrency(eur);
        addCurrency(usd);
        addCurrency(gbp);
        addCurrency(jpy);
    }

    // Добавляем валюту в репозиторий
    public void addCurrency(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    // Получаем валюту по коду (например, "USD", "EUR")
    public Currency getCurrencyByCode(String code) {
        return currencies.get(code);
    }

    // Получаем все валюты
    public Map<String, Currency> getAllCurrencies() {
        return currencies;
    }
    */
}