package ru.iliya.services;

import ru.iliya.entities.Friends;

import java.util.List;

public interface FriendsDataBaseService {

    void deleteFriendsByFriendID(int friendID);

    List<Friends> findByUserID(int userID);

    List<Friends> findByUserIDAndUser2ID(int userID, int user2ID);

    List<Friends> findByUser2ID(int user2ID);

    void setFriendsByUserIDAndUser2ID(int userID, int user2ID);
}
