package exceptionsUtils;

public class UserException extends Exception{

    // если пользователь заблокирован, операции не могут быть выполнены.
    public UserException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
