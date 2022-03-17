package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "recommendations")
public class Recommendations {
    private int recommendationID;
    private Date dateRecommendation;
    private User user;
    private Book book;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    public int getRecommendationID() {
        return this.recommendationID;
    }

    public void setRecommendationID(int recommendationID) {
        this.recommendationID = recommendationID;
    }

    @Column(name = "date_recommendation")
    public void setDateRecommendation(Date dateRecommendation) {
        this.dateRecommendation = dateRecommendation;
    }

    public Date getDateRecommendation() {
        return this.dateRecommendation;
    }

    @Override
    public String toString() {
        return "Recommendations{" +
                "recommendationID=" + recommendationID +
                ", dateRecommendation=" + dateRecommendation +
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
