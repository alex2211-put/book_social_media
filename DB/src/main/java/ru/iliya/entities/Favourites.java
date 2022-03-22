package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favourites")
public class Favourites {
    private int linkID;
    private Date dateFavourite;
    private User user;
    private Book book;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    public int getLinkID() {
        return this.linkID;
    }

    public void setLinkID(int linkID) {
        this.linkID = linkID;
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
                ", dateFavourite=" + dateFavourite +
                ", user=" + user +
                ", book=" + book +
                '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
