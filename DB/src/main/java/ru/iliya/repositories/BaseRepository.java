package ru.iliya.repositories;

import ru.iliya.entities.Book;
import ru.iliya.entities.Marks;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BaseRepository{
    Book addBook(Book book);
    Book getByTitle(String title);
    List <Book> findByAuthor(String firstname, String lastName);
    List <Book> findByGenre(String genre);
    List<Book> getAll();
    Marks findByBookIdAndUserId(Integer bookId, Integer userId);
    void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark);

}
