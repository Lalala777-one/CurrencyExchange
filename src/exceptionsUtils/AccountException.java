package exceptionsUtils;

public class AccountException extends Exception{

    // счет не найден
    public AccountException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
