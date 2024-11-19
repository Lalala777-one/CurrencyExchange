package model;

import java.util.HashMap;
import java.util.Map;

public class Currency {

    // todo

    // либо оставить так и создать еще один репозиторий?
    // тогда в ExchangeRate надо добавить с какой и в какую валюту?
   // private Currency fromCurrency;  // Исходная валюта
   // private Currency toCurrency;

    private String name;
    private String code;

    Currency(String name, String code ) {
        this.name = name;
        this.code = code;
    }

    private static final Map<String, Currency> currencies = new HashMap<>();

    // Створюємо валюту та додаємо її в карту
    public static final Currency EUR = new Currency("Euro", "EUR");
    public static final Currency USD = new Currency("US Dollar", "USD");
    public static final Currency SEK = new Currency("Swedish Krona", "SEK");
    public static final Currency GBP = new Currency("British Pound", "GBP");
    public static final Currency JPY = new Currency("Japanese Yen", "JPY");
    public static final Currency CHF = new Currency("Swiss Franc", "CHF");

    static {
        currencies.put(EUR.getCode(), EUR);
        currencies.put(USD.getCode(), USD);
        currencies.put(SEK.getCode(), SEK);
        currencies.put(GBP.getCode(), GBP);
        currencies.put(JPY.getCode(), JPY);
        currencies.put(CHF.getCode(), CHF);
    }


    // todo проверить вывод
    @Override
    public String toString() {
        return String.format("Валюта: %12s Код: %6s", name, code);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static Currency findByCode(String code) {
        Currency currency = currencies.get(code);
        if (currency == null) {
            throw new IllegalArgumentException("Unknown currency code: " + code);
        }
        return currency;
    }

    /*
    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
     */
}
