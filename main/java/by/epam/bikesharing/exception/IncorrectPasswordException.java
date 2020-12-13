package by.epam.bikesharing.exception;

public class IncorrectPasswordException extends Exception {


    public IncorrectPasswordException() {}

    public IncorrectPasswordException(String message) {
        super (message);
    }

    public IncorrectPasswordException(Throwable cause) {
        super (cause);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super (message, cause);
    }
}
