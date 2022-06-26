package store.bookstore.controller.errors;

public class NoPriceException extends IllegalArgumentException{

    private NoPriceException() {}

    public NoPriceException(String message)
    {
        super(message);
    }
}
