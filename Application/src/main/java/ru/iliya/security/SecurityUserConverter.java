package ru.iliya.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.iliya.entities.User;
import ru.iliya.repositories.UserRepository;

@Service
public class SecurityUserConverter {
    @Autowired
    UserRepository userRepository;
    public User getUserByDetails(UserDetails userDetails) {
        return userRepository.findByNickname(userDetails.getUsername());
    }
}
