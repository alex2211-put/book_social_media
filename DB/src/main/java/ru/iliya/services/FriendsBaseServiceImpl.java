package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Friends;
import ru.iliya.repositories.FriendsRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class FriendsBaseServiceImpl implements FriendsBaseService{

    @Autowired
    private FriendsRepository friendsRepository;

    @Override
    @Transactional
    public void deleteFriendsByFriendID(int friendID) {
        friendsRepository.deleteByFriendID(friendID);
    }

    @Override
    public List<Friends> findByUserID(int userID) {
        return friendsRepository.findByUserID(userID);
    }

    @Override
    public List<Friends> findByUserIDAndUser2ID(int userID, int user2ID) {
        return friendsRepository.findByUserIDAndUser2ID(userID, user2ID);
    }

    @Override
    public List<Friends> findByUser2ID(int user2ID) {
        return friendsRepository.findByUser2ID(user2ID);
    }

    @Override
    public void setFriendsByUserIDAndUser2ID(int userID, int user2ID) {
        Friends friends = new Friends();
        friends.setUserID(userID);
        friends.setUser2ID(user2ID);
        Date current = new Date();
        friends.setDateFriendship(current);
        friendsRepository.save(friends);
    }

}
