package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;
import ru.iliya.entities.Favourites;
import ru.iliya.entities.User;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
    List<Favourites> findByUser(User user);
    void deleteFavouritesByLinkID(int linkID);
    void deleteFavouriteByUserAndBook(User user, Book book);
}
