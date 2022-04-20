package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Comments;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private BaseRepository baseRepository;

    @Override
    public List<Comments> findCommentsByBookId(Integer bookId) {
        return baseRepository.findCommentsByBookId(bookId);
    }

    @Override
    public void setCommentByBookId(Integer bookId, Integer userId, String comment) {
        baseRepository.setCommentByBookIdAndUserId(bookId, userId, comment);
    }
}