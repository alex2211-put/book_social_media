package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Favourites;

import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private FavouritesDataBaseService favouritesBaseService;

    @Override
    public void setFavouritesByUserIdAndBookId(int userID, int bookID) {
        favouritesBaseService.setFavouritesByParams(userID, bookID);
    }

    @Override
    public List<Favourites> findFavouritesByUserID(int userID) {
        return favouritesBaseService.findFavouritesByUserID(userID);
    }

    @Override
    public void deleteFavouritesByLinkID(int linkID) {
        favouritesBaseService.deleteFavouritesByLinkID(linkID);
    }

    @Override
    public void deleteFavouriteByUserIdAndBookId(int userId, int bookId) {
        favouritesBaseService.deleteFavouriteByUserIdAndBookId(userId, bookId);
    }
}
