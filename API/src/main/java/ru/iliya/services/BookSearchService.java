package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Book;
import ru.iliya.repositories.BaseRepository;
import ru.iliya.repositories.MongoRepositoryImpl;

import java.util.List;


@Service
public class BookSearchService {

    @Autowired
    BaseRepository baseRepository;

    public Book findBookByTitle(String title) {
        return baseRepository.getByTitle(title);
    }

    public List<Book> findBooksByAuthor(String firstName, String lastName) {
        return baseRepository.findByAuthor(firstName, lastName);
    }

    public List <Book> findBooksByGenre(String genre) {
        return baseRepository.findByGenre(genre);
    }
}
