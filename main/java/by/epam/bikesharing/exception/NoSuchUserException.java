package by.epam.bikesharing.exception;

public class NoSuchUserException extends Exception {


    public NoSuchUserException() {}

    public NoSuchUserException(String message) {
        super (message);
    }

    public NoSuchUserException(Throwable cause) {
        super (cause);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super (message, cause);
    }
}
