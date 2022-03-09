package ru.iliya.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Author;
import ru.iliya.entities.Book;
import ru.iliya.entities.Marks;
import ru.iliya.entities.Comments;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BaseRepositoryImpl implements BaseRepository{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    MarksRepository marksRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }


    @Override
    public Book getByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List <Book> findByAuthor(String firstname, String lastName) {
        Author author = authorRepository.findAuthorByFirstNameAndLastName(firstname, lastName);
        List <Author> authors = new ArrayList<>();
        authors.add(author);
        return bookRepository.findByAuthors(authors);
    }

    @Override
    public List <Book> findByGenre(String genre) {
        return bookRepository.findBooksByGenre(genre);
    }

    @Override
    public Marks findMarksByBookIdAndUserId(Integer bookId, Integer userId) {
        return marksRepository.findMarksByBookIdAndUserId(bookId, userId);
    }

    @Override
    public void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark) {
        Marks marks = marksRepository.findMarksByBookIdAndUserId(bookId, userId);
        if (marks == null) {
            marksRepository.saveAndFlush(new Marks(bookId, userId, mark));
        }
    }

    public List <Comments> findCommentsByBookId(Integer bookId) {
        Book book = bookRepository.findBookByBookID(bookId);
        return commentRepository.findCommentsByBook(book);
    }

    @Override
    public void setCommentByBookId(Integer bookId, String comment) {
        Book book = bookRepository.findBookByBookID(bookId);
        Comments comments = new Comments();
        comments.setBook(book);
        comments.setComment(comment);
        commentRepository.save(comments);
    }
}
