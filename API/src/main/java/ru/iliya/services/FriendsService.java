package ru.iliya.services;

import ru.iliya.entities.Friends;

import java.util.List;

public interface FriendsService {

    void deleteFriendsByFriendID(int friendID);

    List<Friends> findOutgoingRequestsByUserId(int userID);

    List<Friends> findByUserIDAndUser2ID(int userID, int user2ID);

    List<Friends> findIncomingRequestsByUserId(int userID);

    void setFriendsByUserIDAndUser2ID(int userID, int user2ID);
}
