package store.bookstore.controller.errors;

public class EmptyStringException extends IllegalArgumentException{

    private EmptyStringException() {}

    public EmptyStringException(String message)
    {
        super(message);
    }
}
