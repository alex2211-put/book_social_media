package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.BlockedUsers;
import ru.iliya.entities.Favourites;
import ru.iliya.entities.Recommendations;
import ru.iliya.entities.User;
import ru.iliya.repositories.BaseRepository;
import ru.iliya.repositories.BlockedUsersRepository;

import java.util.Date;
import java.util.List;

@Service
public class UserSearchServiceImpl implements UserSearchService{

    @Autowired
    private BaseRepository baseRepository;

    @Override
    public List<User> findUserByFirstName(String firstName) {
        return baseRepository.findUserByFirstName(firstName);
    }

    @Override
    public void setUserByParams(String nickname, String firstName, String lastName, Date birthdate,
                                String email, boolean openProfile, String hashPassword, int roleID) {
        baseRepository.setUserByParams(nickname, firstName, lastName, birthdate, email, openProfile, hashPassword, roleID);
    }

    @Override
    public List<BlockedUsers> findByUserIDBlocked(int userID) {
        return baseRepository.findByUserIDBlocked(userID);
    }

    @Override
    public void setBlockedUsersByParams(int userID, int userIDBlocked) {
        baseRepository.setBlockedUsersByParams(userID, userIDBlocked);
    }


    @Override
    public void setRecommendationsByParams(int userID, int bookID) {
        baseRepository.setRecommendationsByParams(userID, bookID);
    }

    @Override
    public List<Recommendations> findRecommendationsByUserID(int userID) {
        return baseRepository.findRecommendationsByUserID(userID);
    }

    @Override
    public void deleteRecommendationsByRecommendationsID(int recommendationsID) {
        baseRepository.deleteRecommendationsByRecommendationsID(recommendationsID);
    }

    @Override
    public List<User> findUserByLastName(String firstName) {
        return null;
    }

    @Override
    public User findUserByNickname(String nickname) {
        return baseRepository.findUserByNickname(nickname);
    }

    @Override
    public User findUserByEmail(String email) {
        return baseRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByUserID(int userID) {
        return baseRepository.findUserByID(userID);
    }
}
