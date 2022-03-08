package ru.iliya.repositories;

import ru.iliya.entities.Author;
import ru.iliya.entities.Book;
import ru.iliya.entities.Marks;
import ru.iliya.entities.Comments;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BaseRepository{
    Book addBook(Book book);
    Book getByTitle(String title);
    List <Book> findByAuthor(String firstname, String lastName);
    List <Book> findByGenre(String genre);
    List<Book> getAll();
    Marks findMarksByBookIdAndUserId(Integer bookId, Integer userId);
    void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark);

    List <Comments> findCommentsByBookId(Integer bookId);
    void setCommentByBookId(Integer bookId, String comment);
    List <Book> findBooksByTitleAndAuthorsAndGenre(String title,
                                                   List <Author> authors,
                                                   String genre);
    List <Book> findBooksByTitleAndAuthors(String title,
                                           List <Author> authors);
    List <Book> findBooksByTitleAndGenre(String title,
                                         String genre);
    List <Book> findBooksByAuthorsAndGenre(List <Author> authors,
                                           String genre);
    List <Book> findBooksByTitle(String title);
}
