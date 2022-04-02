package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.User;
import ru.iliya.repositories.BaseRepository;
import ru.iliya.services.UserService;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseRepository baseRepository;

    @Override
    public List<User> findUserByFirstName(String firstName) {
        return baseRepository.findUserByFirstName(firstName);
    }

    @Override
    public void setUserByParams(String nickname, String firstName, String lastName, Date birthdate,
                                String email, boolean openProfile, String hashPassword, int roleID, String imageLink) {
        baseRepository.setUserByParams(nickname, firstName, lastName, birthdate, email, openProfile, hashPassword, roleID, imageLink);
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return baseRepository.findUserByLastName(lastName);
    }

    @Override
    public User findUserByNickname(String nickname) {
        return baseRepository.findUserByNickname(nickname);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return baseRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByUserID(int userID) {
        return baseRepository.findUserByID(userID);
    }

    @Override
    public List<User> findUserByFirstNameLastNameNickNameEmail(String search) {
        if (search.contains("@")) {
            return baseRepository.findUserByEmail(search);
        }
        User userByNickname = baseRepository.findUserByNickname(search);
        List<User> usersByFirstName = baseRepository.findUserByFirstName(search);
        List<User> usersByLastName = baseRepository.findUserByLastName(search);
        Set<User> uniqUsers = new HashSet<User>();
        uniqUsers.addAll(usersByFirstName);
        uniqUsers.addAll(usersByLastName);
        if (userByNickname != null) {
            uniqUsers.add(userByNickname);
        }
        return new ArrayList<User>(uniqUsers);
    }
}
