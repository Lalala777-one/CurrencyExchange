package exceptions;

public class CurrencyException extends Exception{

    //  валюта не найдена
    public CurrencyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
