package ru.iliya.services;

import ru.iliya.entities.Favourites;

import java.util.List;

public interface FavouritesService {

    void setFavouritesByUserIdAndBookId(int userID, int bookID);

    List<Favourites> findFavouritesByUserID(int userID);

    void deleteFavouritesByLinkID(int linkID);

    void deleteFavouriteByUserIdAndBookId(int userId, int bookId);
}
