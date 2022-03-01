package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findByFirstName(String firstName);
    User findByNickname(String nickname);
    User findByEmail(String email);
    User findByUserID(int userID);
}
