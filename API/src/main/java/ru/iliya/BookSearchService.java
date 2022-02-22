package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iliya.entities.Book;
import ru.iliya.repositories.BaseRepository;

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
