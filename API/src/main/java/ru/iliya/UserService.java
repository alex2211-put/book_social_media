package ru.iliya;

import ru.iliya.entities.BlockedUsers;
import ru.iliya.entities.Favourites;
import ru.iliya.entities.Recommendations;
import ru.iliya.entities.User;

import java.util.Date;
import java.util.List;

public interface UserSearchService {
    List<User> findUserByFirstName(String firstName);
    List<User> findUserByLastName(String lastName);
    User findUserByNickname(String nickname);
    User findUserByEmail(String email);
    User findUserByUserID(int userID);
    void setUserByParams(String nickname, String firstName, String lastName, Date birthdate,
                                String email, boolean openProfile, String hashPassword, int roleID);
    List<BlockedUsers> findByUserIDBlocked(int userID);
    void setBlockedUsersByParams(int userID, int userIDBlocked);

    void setRecommendationsByParams(int userID, int bookID);
    List<Recommendations> findRecommendationsByUserID(int userID);
    void deleteRecommendationsByRecommendationsID(int recommendationsID);
}
