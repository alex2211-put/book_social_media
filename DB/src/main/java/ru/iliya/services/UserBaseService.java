package ru.iliya.services;

import ru.iliya.entities.User;

import java.util.Date;
import java.util.List;

public interface UserBaseService {

    void setUserByParams(String nickname, String firstName, String lastName, Date birthdate, String email, boolean openProfile, String hashPassword, int roleID, String imageLink);

    List<User> findUserByFirstName(String firstName);

    List<User> findUserByLastName(String lastName);

    List<User> findUserByEmail(String email);

    User findUserByNickname(String nickname);

    User findUserByID(int userID);
}
