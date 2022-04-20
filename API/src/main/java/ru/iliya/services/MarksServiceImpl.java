package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Marks;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class MarksServiceImpl implements MarksService{

    @Autowired
    BaseRepository baseRepository;

    @Override
    public Marks findByBookIdAndUserId(Integer bookId, Integer userId) {
        return baseRepository.findMarksByBookIdAndUserId(bookId, userId);
    }

    @Override
    public List<Marks> findAllMarks(Integer bookId) {
        return baseRepository.findMarksByBookId(bookId);
    }

    @Override
    public void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark) {
        baseRepository.setMarksByBookIdAndUserId(bookId, userId, mark);
    }

    @Override
    public void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId) {
        baseRepository.deleteMarkByBookIdAndUserId(bookId, userId);
    }
}
