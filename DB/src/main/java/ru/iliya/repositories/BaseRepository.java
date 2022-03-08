package ru.iliya.repositories;

import ru.iliya.entities.*;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BaseRepository{
    Book addBook(Book book);
    Book getByTitle(String title);
    List <Book> findByAuthor(String firstname, String lastName);
    List <Book> findByGenre(String genre);
    List<Book> getAll();

    Marks findByBookIdAndUserId(Integer bookId, Integer userId);
    void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark);

    List <Comments> findCommentsByBookId(Integer bookId);
    void setCommentByBookId(Integer bookId, String comment);
    void deleteCommentsByCommentId(int commentId);

    void setUserByParams(String nickname, String firstName, String lastName, Date birthdate, String email, boolean openProfile, String hashPassword, int roleID);
    List<User> findUserByFirstName(String firstName);
    List<User> findUserByLastName(String lastName);
    User findUserByEmail(String email);
    User findUserByNickname(String nickname);
    User findUserByID(int userID);

    void setFavouritesByParams(int userID, int bookID);
    List<Favourites> findFavouritesByUserID(int userID);
    void deleteFavouritesByLinkID(int linkID);

    void setRecommendationsByParams(int userID, int bookID);
    List<Recommendations> findRecommendationsByUserID(int userID);
    void deleteRecommendationsByRecommendationsID(int recommendationsID);

    void deleteBlockedUsersByBlockID(int blockID);
    List<BlockedUsers> findByUserIDBlocked(int userIDBlocked);
    void setBlockedUsersByParams(int userID, int userIDBlocked);

}
