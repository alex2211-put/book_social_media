package ru.iliya.repositories;

import ru.iliya.entities.Book;
import ru.iliya.entities.Comments;

import java.util.List;

public interface BaseRepository{
    Book addBook(Book book);
    Book getByTitle(String title);
    List <Book> findByAuthor(String firstname, String lastName);
    List <Book> findByGenre(String genre);
    List<Book> getAll();
    List <Comments> findCommentsByBookId(Integer bookId);
    void setCommentByBookId(Integer bookId, String comment);
}
