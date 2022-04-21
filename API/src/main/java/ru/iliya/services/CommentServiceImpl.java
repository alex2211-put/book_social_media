package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Comments;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentBaseService commentBaseService;

    @Override
    public List<Comments> findCommentsByBookId(Integer bookId) {
        return commentBaseService.findCommentsByBookId(bookId);
    }

    @Override
    public void setCommentByBookIdAndUserId(Integer bookId, Integer userId, String comment) {
        commentBaseService.setCommentByBookIdAndUserId(bookId, userId, comment);
    }
}