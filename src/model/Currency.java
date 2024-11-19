package model;

public enum Currency {

    // todo

    // либо оставить так и создать еще один репозиторий?
    // тогда в ExchangeRate надо добавить с какой и в какую валюту?
   // private Currency fromCurrency;  // Исходная валюта
   // private Currency toCurrency;

    EUR("Euro", "EUR"),
    USD("US Dollar", "USD"),
    SEK("Swedish Krona", "SEK"),
    GBP("British Pound", "GBP"),
    JPY("Japanese Yen", "JPY"),
    CHF("Swiss Franc", "CHF");

    private String name;
    private String code;

    Currency(String name, String code ) {
        this.name = name;
        this.code = code;
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
        for (Currency currency : values()) {
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unknown currency code: " + code);
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
