package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
