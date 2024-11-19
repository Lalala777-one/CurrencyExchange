package repository;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateInitializer {

    public static Map<String, Double> getInitialExchangeRates() {
        Map<String, Double> rates = new HashMap<>();

        rates.put("EUR-USD", 1.08); // 1 EUR = 1.08 USD
        rates.put("EUR-SEK", 11.5);
        rates.put("EUR-GBP", 0.88);
        rates.put("EUR-JPY", 150.0);
        rates.put("EUR-CHF", 0.95);

        return rates;
    }
}
