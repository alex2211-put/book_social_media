package ru.iliya.services;

import ru.iliya.entities.Marks;

import java.util.List;

public interface MarksBaseService {

    Marks findMarksByBookIdAndUserId(Integer bookId, Integer userId);

    void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark);

    List<Marks> findMarksByBookId(Integer bookId);

    void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId);
}
