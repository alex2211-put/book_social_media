package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Friends;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class FriendsService {
    @Autowired
    private BaseRepository baseRepository;

    public void deleteFriendsByFriendID(int friendID) {
        baseRepository.deleteFriendsByFriendID(friendID);
    }

    public List<Friends> findByUserID(int userID) {
        return baseRepository.findByUserID(userID);
    }

    public List<Friends> findByUserIDAndUser2ID(int userID, int user2ID) {
        return baseRepository.findByUserIDAndUser2ID(userID, user2ID);
    }

    public List<Friends> findByUser2ID(int user2ID) {
        return baseRepository.findByUser2ID(user2ID);
    }

    public void setFriendsByUserIDAndUser2ID(int userID, int user2ID) {
        baseRepository.setFriendsByUserIDAndUser2ID(userID, user2ID);
    }
}
