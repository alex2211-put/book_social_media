package ru.iliya.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;

import java.util.List;

@Repository
public class BaseRepositoryImpl implements BaseRepository{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        Book savedBook = bookRepository.saveAndFlush(book);
        return savedBook;
    }


    @Override
    public Book getByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
