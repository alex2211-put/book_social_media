package ru.iliya.repositories;


import ru.iliya.entities.Book;

import java.util.List;

public interface BaseRepository{
    Book getByTitle(String title);
}
