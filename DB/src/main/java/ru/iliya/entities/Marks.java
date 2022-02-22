package ru.iliya.entities;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {

    private int markId;
    private int bookId;
    private int userId;
    private int mark;

    public Marks(int bookId, int userId, int mark) {
        this.userId = userId;
        this.bookId = bookId;
        this.mark = mark;
    }
    public  Marks() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    public int getMarkId() {return markId;}
    public void setMarkId(int markId) {this.markId = markId;}

    @Column(name = "book_id")
    public int getBookId() {return bookId;}
    public void setBookId(int bookId) {this.bookId = bookId;}

    @Column(name = "user_id")
    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    @Column(name = "mark")
    public int getMark() {return mark;}
    public void setMark(int mark) {this.mark = mark;}

    @Override
    public String toString() {
        return "Marks{" +
                "markId=" + markId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", mark=" + mark +
                '}';
    }
}
