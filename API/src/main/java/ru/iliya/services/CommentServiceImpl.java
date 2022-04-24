package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Comments;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentsDataBaseService commentsDataBaseService;

    @Override
    public List<Comments> findCommentsByBookId(Integer bookId) {
        return commentsDataBaseService.findCommentsByBookId(bookId);
    }

    @Override
    public void setCommentByBookIdAndUserId(Integer bookId, Integer userId, String comment) {
        commentsDataBaseService.setCommentByBookIdAndUserId(bookId, userId, comment);
    }
}