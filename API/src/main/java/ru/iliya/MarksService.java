package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Marks;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class MarksService {
    @Autowired
    BaseRepository baseRepository;
    Marks findByBookIdAndUserId(Integer bookId, Integer userId) {
        return baseRepository.findMarksByBookIdAndUserId(bookId, userId);
    }
    void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark) {
        baseRepository.setMarksByBookIdAndUserId(bookId, userId, mark);
    }
}
