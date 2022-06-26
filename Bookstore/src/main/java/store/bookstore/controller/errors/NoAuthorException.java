package store.bookstore.controller.errors;

public class NoAuthorException extends IllegalArgumentException{

    private NoAuthorException() {}

    public NoAuthorException(String message)
    {
        super(message);
    }
}
