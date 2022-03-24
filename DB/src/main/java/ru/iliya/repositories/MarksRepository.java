package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Marks;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {

    Marks findMarksByBookIdAndUserId(Integer bookId, Integer userIid);

    List<Marks> findMarksByBookId(Integer bookId);
    @Transactional
    void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId);
}
