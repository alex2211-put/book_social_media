package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "friends")
public class Friends {
    private int friendID;
    private int user1ID;
    private int user2ID;
    private Date dateFriendship;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    public int getFriendID() { return this.friendID; }
    public void setFriendID(int friendID) { this.friendID = friendID; }

    @Column(name = "user1_id")
    public int getUser1ID() { return this.user1ID; }
    public void setUser1ID(int user1ID) { this.user1ID = user1ID; }

    @Column(name = "user2_id")
    public int getUser2ID() { return this.user2ID; }
    public void setUser2ID(int user1ID) { this.user2ID = user2ID; }

    @Column(name = "date_friendship")
    public void setDateFriendship(Date dateFriendship) {
        this.dateFriendship = dateFriendship;
    }
    public Date getDateFriendship() { return this.dateFriendship; }

    @Override
    public String toString() {
        return "Friends{" +
                "friendID=" + friendID +
                ", user1ID='" + user1ID + '\'' +
                ", user2ID='" + user2ID + '\'' +
                ", dateFriendship=" + dateFriendship +
                '}';
    }
}
