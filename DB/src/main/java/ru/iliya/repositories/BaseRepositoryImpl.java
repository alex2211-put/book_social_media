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

    @Override
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

    @Override
    public List <Book> findBooksByTitleAndAuthorsAndGenre(String title,
                                                   List <Author> authors,
                                                   String genre) {
        List <Book> books = bookRepository.findBooksByTitleAndAuthorsAndGenre(title, authors, genre);
        if (!books.isEmpty()) {
            return books;
        }
        else {return bookRepository.findBooksByTitleLikeAndAuthorsAndGenre(
                title + "%", authors, genre);}
    }
    @Override
    public List <Book> findBooksByTitleAndAuthors(String title,
                                           List <Author> authors) {
        List <Book> books = bookRepository.findBooksByTitleAndAuthors(title, authors);
        if (!books.isEmpty()) {
            return books;
        }
        else {return bookRepository.findBooksByTitleLikeAndAuthors(title + "%", authors);}
    }
    @Override
    public List <Book> findBooksByTitleAndGenre(String title,
                                         String genre){
        List <Book> books = bookRepository.findBooksByTitleAndGenre(title, genre);
        if (!books.isEmpty()) {
            return books;
        }
        else { return bookRepository.findBooksByTitleLikeAndGenre(title + "%", genre);}
    }
    @Override
    public List <Book> findBooksByAuthorsAndGenre(List <Author> authors,
                                           String genre) {
        return bookRepository.findBooksByAuthorsAndGenre(authors, genre);
    }
    @Override
    public List <Book> findBooksByTitle(String title) {
        List <Book> books = bookRepository.findBooksByTitle(title);
        if (!books.isEmpty()) {
            return books;
        }
        else {
            return bookRepository.findBooksByTitleLike(title + "%");
        }
    }
}
