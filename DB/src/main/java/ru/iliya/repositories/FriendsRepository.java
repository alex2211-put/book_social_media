package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Friends;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {

    List<Friends> findByUserID(int userID);

    List<Friends> findByUserIDAndUser2ID(int userID, int user2ID);

    void deleteByFriendID(int friendID);

    List<Friends> findByUser2ID(int user2ID);
}
