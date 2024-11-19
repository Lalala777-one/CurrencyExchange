package model;

public class Currency {

    // todo

    // либо оставить так и создать еще один репозиторий?
    // тогда в ExchangeRate надо добавить с какой и в какую валюту?
   // private Currency fromCurrency;  // Исходная валюта
   // private Currency toCurrency;

    private String name;
    private String code;

    public Currency(String name, String code) {
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
}
