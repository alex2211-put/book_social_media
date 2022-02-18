package ru.iliya.entities;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {
    private Integer commentId;
    private String comment;
    private Book book;

    @Id
    @Column(name = "commentId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCommentId() {
        return this.commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    @JoinColumn(name = "bookId")
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

}
