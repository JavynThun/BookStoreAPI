package store.bookstore.repository;

import store.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface MySqlRepositoryAuthor extends JpaRepository<Author, Integer> {
    Author findByNameAndBirthday(String name, Date birthday);
    Boolean deleteAllByBookId(Integer Id);

}


