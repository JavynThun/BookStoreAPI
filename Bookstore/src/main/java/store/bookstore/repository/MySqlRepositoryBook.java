package store.bookstore.repository;

import store.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MySqlRepositoryBook extends JpaRepository<Book, Integer> {
    Book findByIsbn(String isbn);
    List<Book> findByTitle(String title);
}


