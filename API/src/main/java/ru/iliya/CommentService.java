package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Comments;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private BaseRepository baseRepository;

    public List<Comments> findCommentsByBookId(Integer bookId) {
        return baseRepository.findCommentsByBookId(bookId);
    }

    public void setCommentByBookId(Integer bookId, String comment) {
        baseRepository.setCommentByBookId(bookId, comment);
    }
}
