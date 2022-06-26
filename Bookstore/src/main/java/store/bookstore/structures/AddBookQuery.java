package store.bookstore.structures;

import store.bookstore.model.Author;

import java.util.List;

public class AddBookQuery {
    private String isbn;
    private String title;
    private List<Author> authors;
    private Integer year;
    private Double price;
    private String genre;

    public Integer getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }
}
