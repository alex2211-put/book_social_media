package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iliya.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
