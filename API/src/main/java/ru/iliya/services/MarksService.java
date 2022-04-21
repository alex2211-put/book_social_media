package ru.iliya.services;

import ru.iliya.entities.Marks;

import java.util.List;

public interface MarksService {

    public Marks findByBookIdAndUserId(Integer bookId, Integer userId);

    public List<Marks> findAllMarksByBookId(Integer bookId);

    public void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark);

    public void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId);
}
