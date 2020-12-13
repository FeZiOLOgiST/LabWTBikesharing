package by.epam.bikesharing.exception;

public class TransactionException extends Exception {


    public TransactionException() {}

    public TransactionException(String message) {
        super (message);
    }

    public TransactionException(Throwable cause) {
        super (cause);
    }

    public TransactionException(String message, Throwable cause) {
        super (message, cause);
    }
}
