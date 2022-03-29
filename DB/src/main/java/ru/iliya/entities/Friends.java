package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "friends")
public class Friends {
    private int friendID;
    private int userID;
    private int user2ID;
    private Date dateFriendship;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    public int getFriendID() {
        return this.friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    @Column(name = "user1_id")
    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "user2_id")
    public int getUser2ID() {
        return this.user2ID;
    }

    public void setUser2ID(int user2ID) {
        this.user2ID = user2ID;
    }

    @Column(name = "date_friendship")
    public void setDateFriendship(Date dateFriendship) {
        this.dateFriendship = dateFriendship;
    }

    public Date getDateFriendship() {
        return this.dateFriendship;
    }


    @Override
    public String toString() {
        return "Friends{" +
                "friendID=" + friendID +
                ", user1ID='" + userID + '\'' +
                ", user2ID='" + user2ID + '\'' +
                ", dateFriendship=" + dateFriendship +
                '}';
    }

    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = user;
    }
}
