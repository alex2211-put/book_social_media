package ru.iliya.services;

import ru.iliya.entities.Comments;

import java.util.List;

public interface CommentsDataBaseService {
    List<Comments> findCommentsByBookId(Integer bookId);

    void setCommentByBookIdAndUserId(Integer bookId, Integer userId, String comment);
}
