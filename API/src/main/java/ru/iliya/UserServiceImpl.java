package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.BlockedUsers;
import ru.iliya.entities.Recommendations;
import ru.iliya.entities.User;
import ru.iliya.repositories.BaseRepository;

import java.util.Date;
import java.util.List;

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
                                String email, boolean openProfile, String hashPassword, int roleID) {
        baseRepository.setUserByParams(nickname, firstName, lastName, birthdate, email, openProfile, hashPassword, roleID);
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
