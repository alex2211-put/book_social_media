package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.iliya.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.title = :title")
    Book getByTitle(@Param("title") String title);
}
