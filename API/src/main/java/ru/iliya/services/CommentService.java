package ru.iliya.services;

import ru.iliya.entities.Comments;

import java.util.List;

public interface CommentService {

    List<Comments> findCommentsByBookId(Integer bookId);

    void setCommentByBookId(Integer bookId, Integer userId, String comment);
}
