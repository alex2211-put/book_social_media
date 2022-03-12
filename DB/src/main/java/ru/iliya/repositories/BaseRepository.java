package ru.iliya.repositories;

import ru.iliya.entities.Book;
import ru.iliya.entities.Marks;
import ru.iliya.entities.Comments;
import ru.iliya.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface BaseRepository{
    Book addBook(Book book);
    Book getByTitle(String title);
    List <Book> findByAuthor(String firstname, String lastName);
    List <Book> findByGenre(String genre);
    List<Book> getAll();
    Marks findMarksByBookIdAndUserId(Integer bookId, Integer userId);
    void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark);

    void setUserByParams(String nickname, String firstName, String lastName, Date birthdate, String email, boolean openProfile, String hashPassword, int roleID);
    List<User> findUserByFirstName(String firstName);
    List<User> findUserByLastName(String lastName);
    User findUserByEmail(String email);
    User findUserByNickname(String nickname);
    User findUserByID(int userID);

    List <Comments> findCommentsByBookId(Integer bookId);
    void setCommentByBookId(Integer bookId, String comment);
}
