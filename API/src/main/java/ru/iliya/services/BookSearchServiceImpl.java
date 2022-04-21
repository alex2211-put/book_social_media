package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Author;

import ru.iliya.entities.Book;
import ru.iliya.entities.Comments;
import ru.iliya.repositories.AuthorRepository;
import ru.iliya.repositories.BaseRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookSearchServiceImpl implements BookSearchService{

    @Autowired
    BaseRepository baseRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CommentBaseService commentBaseService;

    @Override
    public Book findBookById(String bookId) {
        return baseRepository.findBookByBookID(Integer.parseInt(bookId));
    }

    @Override
    public Book findBookByTitle(String title) {
        return baseRepository.getByTitle(title);
    }

    @Override
    public List<Book> findBooksByAuthor(String firstName, String lastName) {
        return baseRepository.findByAuthor(firstName, lastName);
    }

    @Override
    public List <Book> findBooksByGenre(String genre) {
        return baseRepository.findByGenre(genre);
    }

    @Override
    public List <Book> findBooksByTitleAuthorGenre(String title, String authorName, String genre) {
        boolean titleIsEmpty = title.isEmpty();
        boolean authorIsEmpty = authorName.isEmpty();
        boolean genreIsEmpty = genre.isEmpty();
        String[] names = authorName.split(" ", 2);

        if (titleIsEmpty && authorIsEmpty && !genreIsEmpty) {
            return baseRepository.findByGenre(genre);
        }
        if (titleIsEmpty && !authorIsEmpty && genreIsEmpty) {
            return baseRepository.findByAuthor(names);
        }
        if (!titleIsEmpty && authorIsEmpty && genreIsEmpty ) {
            return baseRepository.findBooksByTitle(title);
        }
        if (titleIsEmpty && !authorIsEmpty && !genreIsEmpty) {
            List <Author> authors = new ArrayList<>();
            Author author = new Author();
            if (names.length == 2) {
                author.setFirstName(names[0]);
                author.setLastName(names[1]);
            }
            else {
                author = authorRepository.findByLastName(names[0]);
            }
            authors.add(author);
            return baseRepository.findBooksByAuthorsAndGenre(authors, genre);
        }
        if (!titleIsEmpty && authorIsEmpty && !genreIsEmpty) {
            return baseRepository.findBooksByTitleAndGenre(title, genre);
        }
        if (!titleIsEmpty && !authorIsEmpty ) {
            List <Author> authors = new ArrayList<>();
            Author author = new Author();
            if (names.length == 2) {
                author.setFirstName(names[0]);
                author.setLastName(names[1]);
            }
            else {
                author = authorRepository.findByLastName(names[0]);
            }
            authors.add(author);
            if (genreIsEmpty) {
                return baseRepository.findBooksByTitleAndAuthors(title, authors);
            }
            else { return baseRepository.findBooksByTitleAndAuthorsAndGenre(title, authors, genre);}
        }
        return new ArrayList<>();
    }

    @Override
    public List<Comments> getCommentsByBookId(String bookId) {
        return commentBaseService.findCommentsByBookId(Integer.parseInt(bookId));
    }

    @Override
    public void addComment(String bookId, String userId, String comment) {
        commentBaseService.setCommentByBookIdAndUserId(Integer.parseInt(bookId), Integer.parseInt(userId), comment);
    }
}