package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Book;
import ru.iliya.entities.Comments;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Book> {
    List<Comments> findCommentsByBook(Book book);
    void deleteCommentsByCommentId(int commentId);
}
