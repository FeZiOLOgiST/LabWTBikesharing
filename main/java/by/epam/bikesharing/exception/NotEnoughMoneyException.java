package by.epam.bikesharing.exception;

public class NotEnoughMoneyException extends Exception {


    public NotEnoughMoneyException() {}

    public NotEnoughMoneyException(String message) {
        super (message);
    }

    public NotEnoughMoneyException(Throwable cause) {
        super (cause);
    }

    public NotEnoughMoneyException(String message, Throwable cause) {
        super (message, cause);
    }
}
