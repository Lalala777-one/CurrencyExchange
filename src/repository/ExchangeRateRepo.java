package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 // todo
    // обхединить сущности Currency и ExchangeRate ??
    // тогда можно создать общий репозиторий Currency который будет вкл и курс валют
    // либо оставить так и создать еще один репозиторий?
    // тогда в ExchangeRate надо добавить с какой и в какую валюту?
   // private Currency fromCurrency;  // Исходная валюта
   // private Currency toCurrency;
 */
public class ExchangeRateRepo {

   private Map<Currency, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();


}
