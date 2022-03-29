package ru.iliya.repositories;

import ru.iliya.entities.*;

import java.util.Date;
import java.util.List;

public interface BaseRepository{

    Book addBook(Book book);
    Book getByTitle(String title);
    Book findBookByBookID(Integer bookId);
    List <Book> findByAuthor(String firstname, String lastName);
    List <Book> findByGenre(String genre);
    List<Book> getAll();
    Marks findMarksByBookIdAndUserId(Integer bookId, Integer userId);
    void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark);
    List<Marks> findMarksByBookId(Integer bookId);
    void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId);

    List <Comments> findCommentsByBookId(Integer bookId);
    void setCommentByBookIdAndUserId(Integer bookId, Integer userId, String comment);

    List <Book> findBooksByTitleAndAuthorsAndGenre(String title,
                                                   List <Author> authors,
                                                   String genre);
    List <Book> findBooksByTitleAndAuthors(String title,
                                           List <Author> authors);
    List <Book> findBooksByTitleAndGenre(String title,
                                         String genre);
    List <Book> findBooksByAuthorsAndGenre(List <Author> authors,
                                           String genre);
    List <Book> findBooksByTitle(String title);

    void setUserByParams(String nickname, String firstName, String lastName, Date birthdate, String email, boolean openProfile, String hashPassword, int roleID, String imageLink);
    List<User> findUserByFirstName(String firstName);
    List<User> findUserByLastName(String lastName);

    List<User> findUserByEmail(String email);
    User findUserByNickname(String nickname);
    User findUserByID(int userID);

    void setFavouritesByParams(int userID, int bookID);
    List<Favourites> findFavouritesByUserID(int userID);
    void deleteFavouritesByLinkID(int linkID);
    void deleteFavouriteByUserIdAndBookId(int userId, int bookId);
    void setRecommendationsByParams(int userID, int bookID);
    List<Recommendations> findRecommendationsByUserID(int userID);
    void deleteRecommendationsByRecommendationsID(int recommendationsID);
    Recommendations findRecommendationByUserIdAndBookId(int userId, int bookId);
    Recommendations findRecommendationByRecommendationId(int recommendationId);
    void deleteBlockedUsersByBlockID(int blockID);
    List<BlockedUsers> findByUserIDBlocked(int userIDBlocked);
    void setBlockedUsersByParams(int userID, int userIDBlocked);
}
