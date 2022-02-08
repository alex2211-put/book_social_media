package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iliya.entities.Friends;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
}
