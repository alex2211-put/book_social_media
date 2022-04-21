package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Friends;

import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService{

    @Autowired
    private FriendsBaseService friendsBaseService;

    @Override
    public void deleteFriendsByFriendID(int friendID) {
        friendsBaseService.deleteFriendsByFriendID(friendID);
    }

    @Override
    public List<Friends> findOutgoingRequestsByUserId(int userID) {
        return friendsBaseService.findByUserID(userID);
    }

    @Override
    public List<Friends> findByUserIDAndUser2ID(int userID, int user2ID) {
        return friendsBaseService.findByUserIDAndUser2ID(userID, user2ID);
    }

    @Override
    public List<Friends> findIncomingRequestsByUserId(int user2ID) {
        return friendsBaseService.findByUser2ID(user2ID);
    }

    @Override
    public void setFriendsByUserIDAndUser2ID(int userID, int user2ID) {
        friendsBaseService.setFriendsByUserIDAndUser2ID(userID, user2ID);
    }
}
