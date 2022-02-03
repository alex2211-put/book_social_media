package ru.iliya.repositories;


import ru.iliya.entities.Book;

import java.util.List;

public interface BaseRepository{
    Book addBook(Book book);
    Book getByTitle(String title);
    List<Book> getAll();
}
