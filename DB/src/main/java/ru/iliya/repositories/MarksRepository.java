package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Marks;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {

    List<Marks> findMarksByBookIdAndUserId(Integer bookId, Integer user_id);

}
