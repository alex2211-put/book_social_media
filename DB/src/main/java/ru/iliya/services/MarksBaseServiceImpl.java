package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Marks;
import ru.iliya.repositories.MarksRepository;

import java.util.List;

@Repository
public class MarksBaseServiceImpl implements MarksBaseService{

    @Autowired
    private MarksRepository marksRepository;

    @Override
    public Marks findMarksByBookIdAndUserId(Integer bookId, Integer userId) {
        return marksRepository.findMarksByBookIdAndUserId(bookId, userId);
    }

    @Override
    public void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark) {
        Marks marks = marksRepository.findMarksByBookIdAndUserId(bookId, userId);
        if (marks == null) {
            marksRepository.saveAndFlush(new Marks(bookId, userId, mark));
        }
    }

    @Override
    public List<Marks> findMarksByBookId(Integer bookId) {
        return marksRepository.findMarksByBookId(bookId);
    }

    @Override
    public void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId) {
        marksRepository.deleteMarkByBookIdAndUserId(bookId, userId);
    }
}
