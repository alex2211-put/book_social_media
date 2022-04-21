package ru.iliya.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.iliya.entities.User;
import ru.iliya.repositories.UserRepository;

@Component
public class SecurityUserConverter {
    @Autowired
    UserRepository userRepository;

    private User user;
    public User getUserByDetails(UserDetails userDetails) {
        if (user == null) {
            user = userRepository.findByNickname(userDetails.getUsername());
        }
        return user;
    }
}
