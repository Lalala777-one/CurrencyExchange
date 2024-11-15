package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExchangeRateRepo {

   private Map<Currency, ExchangeRate> exchangeRateMap = new LinkedHashMap<>();
}
