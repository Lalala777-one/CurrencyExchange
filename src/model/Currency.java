package model;

public class Currency {

    // todo
    // обхединить сущности Currency и ExchangeRate ??
    // тогда можно создать общий репозиторий Currency который будет вкл и курс валют
    // либо оставить так и создать еще один репозиторий?
    // тогда в ExchangeRate надо добавить с какой и в какую валюту?
   // private Currency fromCurrency;  // Исходная валюта
   // private Currency toCurrency;

    private String name;
    private String code;
    // todo
   // private double exchangeRate; // Курс валюты относительно базовой валюты (например, к USD)

    // добавить exchangeRate в конструктор
    Currency(String name, String code ) {
        this.name = name;
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
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
