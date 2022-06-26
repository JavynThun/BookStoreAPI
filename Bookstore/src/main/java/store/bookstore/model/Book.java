package store.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String isbn;
    private String title;
    private Integer year ;
    private double price;
    private String genre;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Author> authors;

    public Book() {
    }

    public Book(String isbn, String title, Integer year, double price, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.price = price;
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public String getIsbn(){
        return this.isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getYear(){
        return this.year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getGenre(){
        return this.genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
