package exceptionsUtils;

public class ExchangeRateException extends Exception {

    // неверный курс обмена

    public ExchangeRateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    // Исключение, когда валюта не найдена в системе
    public static class CurrencyNotFoundException extends ExchangeRateException {
        public CurrencyNotFoundException(String currencyCode) {
            super("Валюта с кодом " + currencyCode + " не найдена.");
        }
    }

    public static class ExchangeRateNotFoundException extends ExchangeRateException {
        public ExchangeRateNotFoundException(String fromCurrencyCode, String toCurrencyCode) {
            super("Курс обмена для валют " + fromCurrencyCode + " -> " + toCurrencyCode + " не найден.");
        }
    }
}
/*
  try {
            Currency usd = new Currency("US Dollar", "USD");
            Currency eur = new Currency("Euro", "EUR");
            double amount = 100;

            double convertedAmount = exchangeRateService.convert(usd, eur, amount);
            System.out.println("Конвертированная сумма: " + convertedAmount);
        } catch (CurrencyNotFoundException | ExchangeRateNotFoundException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
 */

