package ru.iliya.repositories;


import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;

import java.util.List;


@Repository
public interface BaseRepository{
    Book getByTitle(String title);
}
