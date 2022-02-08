package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iliya.entities.BlockedUsers;

public interface BlockedUsersRepository extends JpaRepository<BlockedUsers, Integer> {
}
