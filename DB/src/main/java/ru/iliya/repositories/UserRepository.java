package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iliya.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
}
