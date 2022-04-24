package ru.iliya.services;

import ru.iliya.entities.Favourites;

import java.util.List;

public interface FavouritessDataBaseService {

    void setFavouritesByParams(int userID, int bookID);

    List<Favourites> findFavouritesByUserID(int userID);

    void deleteFavouritesByLinkID(int linkID);

    void deleteFavouriteByUserIdAndBookId(int userId, int bookId);
}
