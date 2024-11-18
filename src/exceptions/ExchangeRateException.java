package exceptions;

public class ExchangeRateException extends Exception{

    // неверный курс обмена

    public ExchangeRateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
