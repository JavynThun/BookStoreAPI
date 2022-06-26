package store.bookstore.controller.errors;

public class UsernameInvalidException extends IllegalArgumentException{

    private UsernameInvalidException() {}

    public UsernameInvalidException(String message)
    {
        super(message);
    }
}
