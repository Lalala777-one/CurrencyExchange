package repository;

import model.Currency;
import model.ExchangeRate;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepo {
//    void addExchangeRate(Currency fromCurrency, Currency toCurrency, double rate);
//    void updateExchangeRate(Currency fromCurrency, Currency toCurrency, double rate);
//    double getExchangeRate(Currency fromCurrency, Currency toCurrency); // need
//    void removeExchangeRate(Currency fromCurrency, Currency toCurrency); // need
//    Map<Currency, ExchangeRate> getAllExchangeMap(); // need ??
    void save(ExchangeRate exchangeRate);
    void update(ExchangeRate exchangeRate);

    Optional<ExchangeRate> findExchangeRateByCurrencies(String currencyFrom, String currencyTo);

    List<ExchangeRate> findAllExchangeRates();

    // neew
    double getExchangeRate(Currency fromCurrency, Currency toCurrency);
    void removeExchangeRate(Currency fromCurrency, Currency toCurrency);

}
