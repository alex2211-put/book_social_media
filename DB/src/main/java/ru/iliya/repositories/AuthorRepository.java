package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findПлизByFirstNameLike(String firstName);

    List<Author> findByLastNameLike(String secondName);
    Author findAuthorByFirstNameAndLastName(String firstname, String lastName);
}
