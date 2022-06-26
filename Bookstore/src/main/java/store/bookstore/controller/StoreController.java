package store.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import store.bookstore.controller.errors.EmptyStringException;
import store.bookstore.controller.errors.NoBookFoundException;
import store.bookstore.controller.errors.NoPriceException;
import store.bookstore.structures.AddBookQuery;
import store.bookstore.structures.GetBookQuery;
import store.bookstore.model.*;
import store.bookstore.repository.MySqlRepositoryAuthor;
import store.bookstore.repository.MySqlRepositoryBook;
import store.bookstore.controller.errors.NoAuthorException;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class StoreController {

    @Autowired
    MySqlRepositoryAuthor mySqlRepositoryAuthor;
    @Autowired
    MySqlRepositoryBook mySqlRepositoryBook;
    @GetMapping("/")
    public String getHome() {
        return "Home";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestBody AddBookQuery addBookQuery) {
        if (addBookQuery.getIsbn().isEmpty() || addBookQuery.getTitle().isEmpty()) throw new EmptyStringException("isbn/title cannot be empty");
        if (addBookQuery.getAuthors() == null || addBookQuery.getAuthors().size() == 0) throw new NoAuthorException("Book must contain at least 1 author");
        if (addBookQuery.getPrice() == 0 || addBookQuery.getPrice() == null) throw new NoPriceException("Please input a book with a price");
        Book newBook = new Book(addBookQuery.getIsbn(), addBookQuery.getTitle(),  addBookQuery.getYear() , addBookQuery.getPrice(), addBookQuery.getGenre());
        mySqlRepositoryBook.save(newBook);
        for (Author author : addBookQuery.getAuthors()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            formatter.setTimeZone(TimeZone.getTimeZone("SGT"));
            if (mySqlRepositoryAuthor.findByNameAndBirthday(author.getName(), author.getBirthday()) == null) {
                Author newAuthor = new Author(author.getName(), author.getBirthday(), newBook);
                mySqlRepositoryAuthor.save(newAuthor);
            }

        }
        return addBookQuery.getTitle() + " added to bookstore!";
    }

    @PutMapping("update-book")
    public String updateBook(@RequestBody AddBookQuery addBookQuery) {
        if (addBookQuery.getAuthors() == null || addBookQuery.getAuthors().size() == 0) throw new NoAuthorException("Book must contain at least 1 author");
        if (addBookQuery.getPrice() == 0 || addBookQuery.getPrice() == null) throw new NoPriceException("Please input a book with a price");
        Book currentBook = mySqlRepositoryBook.findByIsbn(addBookQuery.getIsbn());
        if (currentBook == null) throw new NoBookFoundException("No book to update!");
        mySqlRepositoryBook.deleteById(currentBook.getId());
        Book updatedBook = new Book(addBookQuery.getIsbn(), addBookQuery.getTitle(),  addBookQuery.getYear() , addBookQuery.getPrice(), addBookQuery.getGenre());
        mySqlRepositoryBook.save(updatedBook);
        for (Author author : addBookQuery.getAuthors()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            formatter.setTimeZone(TimeZone.getTimeZone("SGT"));
            if (mySqlRepositoryAuthor.findByNameAndBirthday(author.getName(), author.getBirthday()) == null) {
                Author newAuthor = new Author(author.getName(), author.getBirthday(), updatedBook);
                mySqlRepositoryAuthor.save(newAuthor);
            }
        }
        return "Book updated!";
    }

    @GetMapping("get-book")
    public List<Book> getBooks(@RequestBody GetBookQuery getBookQuery) {
        List<Book> resultList = new ArrayList<>();
        List<Book> bookList = mySqlRepositoryBook.findByTitle(getBookQuery.getTitle());
        for (Book book : bookList) {
            Author author = mySqlRepositoryAuthor.findByNameAndBirthday(getBookQuery.getAuthor().getName(), getBookQuery.getAuthor().getBirthday());
            if (book.getAuthors().contains(author)) {
                resultList.add(book);
            }
        }
        if (resultList.size() == 0) throw new NoBookFoundException("No book found!");
        return resultList;
    }

    @DeleteMapping("delete-book")
    public String deleteBook(@RequestBody Map<String, String> body) {
        Book currentBook = mySqlRepositoryBook.findByIsbn(body.get("isbn"));
        if (currentBook == null) throw new NoBookFoundException("Book not found in store!");

        mySqlRepositoryBook.deleteById(currentBook.getId());
        return currentBook.getTitle() + " deleted!";
    }

    @GetMapping("/get-all-books")
    public List<Book> getAllBook() {
        return mySqlRepositoryBook.findAll();
    }
}
