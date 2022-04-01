package ru.iliya.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.*;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BaseRepositoryImpl implements BaseRepository {

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
    @Autowired
    private FavouritesRepository favouritesRepository;
    @Autowired
    private RecommendationsRepository recommendationsRepository;
    @Autowired
    private BlockedUsersRepository blockedUsersRepository;
    @Autowired
    private FriendsRepository friendsRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }


    @Override
    public Book getByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    @Override
    public Book findBookByBookID(Integer bookId) {
        return bookRepository.findBookByBookID(bookId);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByAuthor(String firstname, String lastName) {
        Author author = authorRepository.findAuthorByFirstNameAndLastName(firstname, lastName);
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        return bookRepository.findByAuthors(authors);
    }

    @Override
    public List<Book> findBooksByTitleAndAuthorsAndGenre(String title,
                                                         List<Author> authors,
                                                         String genre) {
        List<Book> books = bookRepository.findBooksByTitleAndAuthorsAndGenre(title, authors, genre);
        if (!books.isEmpty()) {
            return books;
        } else {
            return bookRepository.findBooksByTitleLikeAndAuthorsAndGenre(
                    title + "%", authors, genre);
        }
    }

    @Override
    public List<Book> findBooksByTitleAndAuthors(String title,
                                                 List<Author> authors) {
        List<Book> books = bookRepository.findBooksByTitleAndAuthors(title, authors);
        if (!books.isEmpty()) {
            return books;
        } else {
            return bookRepository.findBooksByTitleLikeAndAuthors(title + "%", authors);
        }
    }

    @Override
    public List<Book> findByGenre(String genre) {
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
    public List<Marks> findMarksByBookId(Integer bookId) {
        return marksRepository.findMarksByBookId(bookId);
    }

    @Override
    public void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId) {
        marksRepository.deleteMarkByBookIdAndUserId(bookId, userId);
    }

    @Override
    public List<Comments> findCommentsByBookId(Integer bookId) {
        Book book = bookRepository.findBookByBookID(bookId);
        return commentRepository.findCommentsByBook(book);
    }

    @Override
    public void setCommentByBookIdAndUserId(Integer bookId, Integer userId, String comment) {
        Book book = bookRepository.findBookByBookID(bookId);
        User user = userRepository.findByUserID(userId);
        Comments comments = new Comments();
        comments.setBook(book);
        comments.setComment(comment);
        comments.setUser(user);
        commentRepository.save(comments);
    }

    @Override
    public List<Book> findBooksByAuthorsAndGenre(List<Author> authors,
                                                 String genre) {
        return bookRepository.findBooksByAuthorsAndGenre(authors, genre);
    }

    @Override
    public List<Book> findBooksByTitleAndGenre(String title,
                                               String genre) {
        List<Book> books = bookRepository.findBooksByTitleAndGenre(title, genre);
        if (!books.isEmpty()) {
            return books;
        } else {
            return bookRepository.findBooksByTitleLikeAndGenre(title + "%", genre);
        }
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitle(title);
        if (!books.isEmpty()) {
            return books;
        } else {
            return bookRepository.findBooksByTitleLike(title + "%");
        }
    }


    //users
    @Override
    public void setUserByParams(String nickname, String firstName, String lastName, Date birthdate, String email, boolean openProfile, String hashPassword, int roleID, String imageLink) {
        User user = new User();
        user.setNickname(nickname);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthdate);
        user.setEmail(email);
        user.setOpenProfile(openProfile);
        user.setHashPassword(hashPassword);
        user.setRole(new Role(roleID));
        user.setImageLink(imageLink);
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
    public List<User> findUserByEmail(String email) {
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

    //favourites
    @Override
    public void setFavouritesByParams(int userID, int bookID) {
        Favourites favourites = new Favourites();
        Book book = bookRepository.findBookByBookID(bookID);
        favourites.setBook(book);
        favourites.setUser(userRepository.findByUserID(userID));
        Date current = new Date();
        favourites.setDateFavourite(current);
        favouritesRepository.save(favourites);
    }

    @Override
    public List<Favourites> findFavouritesByUserID(int userID) {
        return favouritesRepository.findByUser(userRepository.findByUserID(userID));
    }

    @Override
    @Transactional
    public void deleteFavouritesByLinkID(int linkID) {
        favouritesRepository.deleteFavouritesByLinkID(linkID);
    }

    @Override
    public void deleteFavouriteByUserIdAndBookId(int userId, int bookId) {
        User user = userRepository.findByUserID(userId);
        Book book = bookRepository.findBookByBookID(bookId);
        favouritesRepository.deleteFavouriteByUserAndBook(user, book);
    }


    //recommendations
    @Override
    public void setRecommendationsByParams(int userID, int bookID) {
        Recommendations recommendations = new Recommendations();
        recommendations.setBook(bookRepository.findBookByBookID(bookID));
        recommendations.setUser(userRepository.findByUserID(userID));
        Date current = new Date();
        recommendations.setDateRecommendation(current);
        recommendationsRepository.save(recommendations);
    }

    @Override
    public List<Recommendations> findRecommendationsByUserID(int userID) {
        return recommendationsRepository.findByUser(userRepository.findByUserID(userID));
    }

    @Override
    @Transactional
    public void deleteRecommendationsByRecommendationsID(int recommendationsID) {
        recommendationsRepository.deleteRecommendationsByRecommendationID(recommendationsID);
    }

    @Override
    public Recommendations findRecommendationByUserIdAndBookId(int userId, int bookId) {
        User user = userRepository.findByUserID(userId);
        Book book = bookRepository.findBookByBookID(bookId);
        return recommendationsRepository.findRecommendationsByBookAndUser(book, user);
    }

    @Override
    public Recommendations findRecommendationByRecommendationId(int recommendationId) {
        return recommendationsRepository.findRecommendationByRecommendationID(recommendationId);
    }


    //blocked users
    @Override
    public void setBlockedUsersByParams(int userID, int userIDBlocked) {
        BlockedUsers blockedUsers = new BlockedUsers();
        blockedUsers.setUserIDBlocked(userIDBlocked);
        blockedUsers.setUserID(userID);
        Date current = new Date();
        blockedUsers.setDateBlock(current);
        blockedUsersRepository.save(blockedUsers);
    }

    @Override
    public List<BlockedUsers> findByUserIDBlocked(int userIDBlocked) {
        return blockedUsersRepository.findByUserIDBlocked(userIDBlocked);
    }

    @Override
    @Transactional
    public void deleteBlockedUsersByBlockID(int blockID) {
        blockedUsersRepository.deleteBlockedUsersByBlockID(blockID);
    }


    //friends
    @Override
    @Transactional
    public void deleteFriendsByFriendID(int friendID) {
        friendsRepository.deleteByFriendID(friendID);
    }

    @Override
    public List<Friends> findByUserID(int userID) {
        return friendsRepository.findByUserID(userID);
    }

    @Override
    public List<Friends> findByUserIDAndUser2ID(int userID, int user2ID) {
        return friendsRepository.findByUserIDAndUser2ID(userID, user2ID);
    }

    @Override
    public void setFriendsByUserIDAndUser2ID(int userID, int user2ID) {
        Friends friends = new Friends();
        friends.setUserID(userID);
        friends.setUser2ID(user2ID);
        Date current = new Date();
        friends.setDateFriendship(current);
        friendsRepository.save(friends);
    }
}
