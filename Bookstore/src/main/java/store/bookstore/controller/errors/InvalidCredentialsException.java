package store.bookstore.controller.errors;

public class InvalidCredentialsException extends IllegalArgumentException{

    private InvalidCredentialsException() {}

    public InvalidCredentialsException(String message)
    {
        super(message);
    }
}
