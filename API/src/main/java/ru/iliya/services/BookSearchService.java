package ru.iliya.services;

import ru.iliya.entities.Book;
import ru.iliya.entities.Comments;

import java.util.List;

public interface BookSearchService {

     Book findBookById(String bookId);

     Book findBookByTitle(String title);

     List<Book> findBooksByAuthor(String firstName, String lastName);

     List <Book> findBooksByGenre(String genre);

     List <Book> findBooksByTitleAuthorGenre(String title, String authorName, String genre);

     List<Comments> getComments(String bookId);

     void addComment(String bookId, String userId, String comment);
}
