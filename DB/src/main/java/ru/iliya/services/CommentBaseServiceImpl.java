package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;
import ru.iliya.entities.Comments;
import ru.iliya.entities.User;
import ru.iliya.repositories.*;

import java.util.List;

@Repository
public class CommentBaseServiceImpl implements CommentBaseService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Comments> findCommentsByBookId(Integer bookId) {
        Book book = bookRepository.findBookByBookID(bookId);
        return commentRepository.findCommentsByBook(book);
    }

    @Override
    public void setCommentByBookIdAndUserId(Integer bookId, Integer userId, String comment) {
        Book book = bookRepository.findBookByBookID(bookId);
        User user = userRepository.findByUserID(userId);
        Comments comments = new Comments();
        comments.setBook(book);
        comments.setComment(comment);
        comments.setUser(user);
        commentRepository.save(comments);
    }
}
