package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Favourites;
import ru.iliya.repositories.BaseRepository;
import ru.iliya.services.FavouritesService;

import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private BaseRepository baseRepository;

    public void setFavouritesByParams(int userID, int bookID) {
        baseRepository.setFavouritesByParams(userID, bookID);
    }

    public List<Favourites> findFavouritesByUserID(int userID) {
        return baseRepository.findFavouritesByUserID(userID);
    }

    public void deleteFavouritesByLinkID(int linkID) {
        baseRepository.deleteFavouritesByLinkID(linkID);
    }

}
