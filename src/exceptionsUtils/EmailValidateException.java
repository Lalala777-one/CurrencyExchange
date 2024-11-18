package exceptionsUtils;

public class EmailValidateException extends Exception {

    /*
    1.
    2.

    getMessage() - возвращает строку с коротким описанием исключения
     */

    public EmailValidateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
