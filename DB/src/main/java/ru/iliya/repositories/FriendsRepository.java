package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Friends;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
}
