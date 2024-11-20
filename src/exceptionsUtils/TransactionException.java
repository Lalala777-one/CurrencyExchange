package exceptionsUtils;

public class TransactionException extends RuntimeException {
  public TransactionException(String message) {
    super(message);
  }
  @Override
  public String getMessage() {
    return super.getMessage();
  }
}
