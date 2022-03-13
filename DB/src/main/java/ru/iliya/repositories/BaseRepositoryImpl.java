package ru.iliya.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BaseRepositoryImpl implements BaseRepository{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MarksRepository marksRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

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

    @Override
    public void setUserByParams(String nickname, String firstName, String lastName, Date birthdate, String email, boolean openProfile, String hashPassword, int roleID) {
        User user = new User();
        user.setNickname(nickname);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthdate);
        user.setEmail(email);
        user.setOpenProfile(openProfile);
        user.setHashPassword(hashPassword);
        user.setRoleID(roleID);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findUserByID(int userID) {
        return userRepository.findByUserID(userID);
    }
}
