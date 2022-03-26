package ru.iliya.services;

import ru.iliya.entities.Favourites;

import java.util.List;

public interface FavouritesService {
    void setFavouritesByParams(int userID, int bookID);

    List<Favourites> findFavouritesByUserID(int userID);

    void deleteFavouritesByLinkID(int linkID);
}
