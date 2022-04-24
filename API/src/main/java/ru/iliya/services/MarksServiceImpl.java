package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Marks;

import java.util.List;

@Service
public class MarksServiceImpl implements MarksService{

    @Autowired
    MarksDataBaseService marksBaseService;

    @Override
    public Marks findByBookIdAndUserId(Integer bookId, Integer userId) {
        return marksBaseService.findMarksByBookIdAndUserId(bookId, userId);
    }

    @Override
    public List<Marks> findAllMarksByBookId(Integer bookId) {
        return marksBaseService.findMarksByBookId(bookId);
    }

    @Override
    public void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark) {
        marksBaseService.setMarksByBookIdAndUserId(bookId, userId, mark);
    }

    @Override
    public void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId) {
        marksBaseService.deleteMarkByBookIdAndUserId(bookId, userId);
    }
}
