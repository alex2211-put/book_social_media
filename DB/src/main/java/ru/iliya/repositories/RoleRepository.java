package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iliya.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
