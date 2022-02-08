package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "recommendations")
public class Recommendations {
    private int recommendationID;
    private int userID;
    private int bookID;
    private Date dateRecommendation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    public int getRecommendationID() { return this.recommendationID; }
    public void setRecommendationID(int recommendationID) { this.recommendationID = recommendationID; }

    @Column(name = "user_id")
    public int getUserID() { return this.userID; }
    public void setUserID(int userID) { this.userID = userID; }

    @Column(name = "book_id")
    public int getBookID() { return this.bookID; }
    public void setBookID(int bookID) { this.bookID = bookID; }

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
                ", userID='" + userID + '\'' +
                ", bookID='" + bookID + '\'' +
                ", dateRecommendation=" + dateRecommendation +
                '}';
    }
}
