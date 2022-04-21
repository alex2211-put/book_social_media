package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;
import ru.iliya.entities.Favourites;
import ru.iliya.entities.User;
import ru.iliya.repositories.BookRepository;
import ru.iliya.repositories.FavouritesRepository;
import ru.iliya.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class FavouritesBaseServiceImpl implements FavouritesBaseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavouritesRepository favouritesRepository;
    @Autowired
    private BookRepository bookRepository;

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
}
