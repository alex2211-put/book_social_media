package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Friends;

import java.util.List;

/// TODO test all methods
@Service
public class FriendsServiceImpl implements FriendsService{

    @Autowired
    private FriendsDataBaseService friendsDataBaseService;

    @Override
    public void deleteFriendsByFriendID(int friendID) {
        friendsDataBaseService.deleteFriendsByFriendID(friendID);
    }

    @Override
    public List<Friends> findOutgoingRequestsByUserId(int userID) {
        return friendsDataBaseService.findByUserID(userID);
    }

    @Override
    public List<Friends> findByUserIDAndUser2ID(int userID, int user2ID) {
        return friendsDataBaseService.findByUserIDAndUser2ID(userID, user2ID);
    }

    @Override
    public List<Friends> findIncomingRequestsByUserId(int user2ID) {
        return friendsDataBaseService.findByUser2ID(user2ID);
    }

    @Override
    public void setFriendsByUserIDAndUser2ID(int userID, int user2ID) {
        friendsDataBaseService.setFriendsByUserIDAndUser2ID(userID, user2ID);
    }
}
