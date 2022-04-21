package ru.iliya.repositories;

import ru.iliya.entities.Author;
import ru.iliya.entities.Book;

import java.util.List;

public interface BaseRepository{

    Book addBook(Book book);

    Book getByTitle(String title);

    Book findBookByBookID(Integer bookId);

    List <Book> findByAuthor(String firstname, String lastname);

    List <Book> findByAuthor(String[] fullName);

    List <Book> findByGenre(String genre);

    List<Book> getAll();

    List <Book> findBooksByTitleAndAuthorsAndGenre(String title, List <Author> authors, String genre);

    List <Book> findBooksByTitleAndAuthors(String title, List <Author> authors);

    List <Book> findBooksByTitleAndGenre(String title, String genre);

    List <Book> findBooksByAuthorsAndGenre(List <Author> authors, String genre);

    List <Book> findBooksByTitle(String title);
}
