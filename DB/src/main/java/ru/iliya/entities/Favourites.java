package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favourites")
public class Favourites {
    private int linkID;
    private int userID;
    private int bookID;
    private Date dateFavourite;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    public int getLinkID() {
        return this.linkID;
    }

    public void setLinkID(int linkID) {
        this.linkID = linkID;
    }

    @Column(name = "user_id")
    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "book_id")
    public int getBookID() {
        return this.bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    @Column(name = "date_favourite")
    public void setDateFavourite(Date dateFavourite) {
        this.dateFavourite = dateFavourite;
    }

    public Date getDateFavourite() {
        return this.dateFavourite;
    }

    @Override
    public String toString() {
        return "Favourites{" +
                "linkID=" + linkID +
                ", userID='" + userID + '\'' +
                ", bookID='" + bookID + '\'' +
                ", dateFavourites=" + dateFavourite +
                '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
