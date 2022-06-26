package store.bookstore.structures;

import store.bookstore.model.Author;

public class GetBookQuery {
    private String title;

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    private Author author;
}
