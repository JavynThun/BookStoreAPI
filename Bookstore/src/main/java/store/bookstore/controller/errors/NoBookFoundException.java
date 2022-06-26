package store.bookstore.controller.errors;

public class NoBookFoundException extends IllegalArgumentException{

    private NoBookFoundException() {}

    public NoBookFoundException(String message)
    {
        super(message);
    }
}
