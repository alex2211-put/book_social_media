package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Friends;
import ru.iliya.repositories.BaseRepository;

@Service
public class FriendsService {
    @Autowired
    BaseRepository baseRepository;
    public Friends findFriendsByUserIDAndUser2ID(int userID, int user2ID) {
        return baseRepository.findFriendsByUserIDAndUser2ID(userID, user2ID);
    }

    public void setFriendsByUserIDAndUser2ID(int userID, int user2ID) {
        baseRepository.setFriendsByUserIDAndUser2ID(userID, user2ID);
    }

    public void deleteFriendsByFriendID(int friendID) {
        baseRepository.deleteFriendsByFriendID(friendID);
    }
}
