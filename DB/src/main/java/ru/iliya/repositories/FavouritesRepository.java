package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iliya.entities.Favourites;

import java.util.List;

public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
    List<Favourites> findByUserID(int userID);
    void deleteFavouritesByLinkID(int linkID);
}
