package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.User;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataBaseService userDataBaseService;

    @Override
    public List<User> findUserByFirstName(String firstName) {
        return userDataBaseService.findUserByFirstName(firstName);
    }

    @Override
    public void setUserByParams(String nickname, String firstName, String lastName, Date birthdate,
                                String email, boolean openProfile, String hashPassword, int roleID, String imageLink) {
        userDataBaseService.setUserByParams(nickname, firstName, lastName, birthdate, email, openProfile, hashPassword, roleID, imageLink);
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return userDataBaseService.findUserByLastName(lastName);
    }

    @Override
    public User findUserByNickname(String nickname) {
        return userDataBaseService.findUserByNickname(nickname);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userDataBaseService.findUserByEmail(email);
    }

    @Override
    public User findUserByUserID(int userID) {
        return userDataBaseService.findUserByID(userID);
    }

    @Override
    public List<User> findUserByFirstNameLastNameNickNameEmail(String search) {
        if (search.contains("@")) {
            return userDataBaseService.findUserByEmail(search);
        }
        User userByNickname = userDataBaseService.findUserByNickname(search);
        List<User> usersByFirstName = userDataBaseService.findUserByFirstName(search);
        List<User> usersByLastName = userDataBaseService.findUserByLastName(search);
        Set<User> uniqUsers = new HashSet<User>();
        uniqUsers.addAll(usersByFirstName);
        uniqUsers.addAll(usersByLastName);
        if (userByNickname != null) {
            uniqUsers.add(userByNickname);
        }
        return new ArrayList<User>(uniqUsers);
    }
}
