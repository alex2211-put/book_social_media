package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.iliya.entities.BlockedUsers;
import ru.iliya.entities.Book;
import ru.iliya.entities.User;

import java.util.List;

public interface BlockedUsersRepository extends JpaRepository<BlockedUsers, Integer> {
    List<BlockedUsers> findByUserIDBlocked(int userIDBlocked);
    void deleteBlockedUsersByBlockID(int blockID);
}
