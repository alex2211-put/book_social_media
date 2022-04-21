package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.User;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBaseService userBaseService;

    @Override
    public List<User> findUserByFirstName(String firstName) {
        return userBaseService.findUserByFirstName(firstName);
    }

    @Override
    public void setUserByParams(String nickname, String firstName, String lastName, Date birthdate,
                                String email, boolean openProfile, String hashPassword, int roleID, String imageLink) {
        userBaseService.setUserByParams(nickname, firstName, lastName, birthdate, email, openProfile, hashPassword, roleID, imageLink);
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return userBaseService.findUserByLastName(lastName);
    }

    @Override
    public User findUserByNickname(String nickname) {
        return userBaseService.findUserByNickname(nickname);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userBaseService.findUserByEmail(email);
    }

    @Override
    public User findUserByUserID(int userID) {
        return userBaseService.findUserByID(userID);
    }

    @Override
    public List<User> findUserByFirstNameLastNameNickNameEmail(String search) {
        if (search.contains("@")) {
            return userBaseService.findUserByEmail(search);
        }
        User userByNickname = userBaseService.findUserByNickname(search);
        List<User> usersByFirstName = userBaseService.findUserByFirstName(search);
        List<User> usersByLastName = userBaseService.findUserByLastName(search);
        Set<User> uniqUsers = new HashSet<User>();
        uniqUsers.addAll(usersByFirstName);
        uniqUsers.addAll(usersByLastName);
        if (userByNickname != null) {
            uniqUsers.add(userByNickname);
        }
        return new ArrayList<User>(uniqUsers);
    }
}
