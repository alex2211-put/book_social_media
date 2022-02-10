package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iliya.entities.Favourites;

public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
}
