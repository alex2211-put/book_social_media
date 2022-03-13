package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blocked_users")
public class BlockedUsers {
    private int blockID;
    private int userID;
    private int userIDBlocked;
    private Date dateBlock;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    public int getBlockID() {
        return this.blockID;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }

    @Column(name = "user_id")
    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "user_id_blocked")
    public int getUserIDBlocked() {
        return this.userIDBlocked;
    }

    public void setUserIDBlocked(int userIDBlocked) {
        this.userIDBlocked = userIDBlocked;
    }

    @Column(name = "date_block")
    public void setDateBlock(Date dateBlock) {
        this.dateBlock = dateBlock;
    }

    public Date getDateBlock() {
        return this.dateBlock;
    }

    @Override
    public String toString() {
        return "BlockedUsers{" +
                "blockID=" + blockID +
                ", userIDMain='" + userID + '\'' +
                ", userIDBlocked='" + userIDBlocked + '\'' +
                ", dateBlock=" + dateBlock +
                '}';
    }

    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIDmain")
    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = user;
    }
}
