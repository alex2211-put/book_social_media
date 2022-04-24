package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Role;
import ru.iliya.entities.User;
import ru.iliya.repositories.UserRepository;

import java.util.Date;
import java.util.List;

@Repository
public class UserDataBaseServiceImpl implements UserDataBaseService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void setUserByParams(String nickname, String firstName, String lastName, Date birthdate, String email, boolean openProfile, String hashPassword, int roleID, String imageLink) {
        User user = new User();
        user.setNickname(nickname);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthdate);
        user.setEmail(email);
        user.setOpenProfile(openProfile);
        user.setHashPassword(hashPassword);
        user.setRole(new Role(roleID));
        user.setImageLink(imageLink);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public List<User> findUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User findUserByID(int userID) {
        return userRepository.findByUserID(userID);
    }

}
