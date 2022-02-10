package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blocked_users")
public class BlockedUsers {
    private int blockID;
    private int userIDMain;
    private int userIDBlocked;
    private Date dateBlock;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    public int getBlockID() { return this.blockID; }
    public void setBlockID(int authorId) { this.blockID = blockID; }

    @Column(name = "user_id_main")
    public int getUserIDMain() { return this.userIDMain; }
    public void setUserIDMain(int userIDMain) { this.userIDMain = userIDMain; }

    @Column(name = "user_id_blocked")
    public int getUserIDBlocked() { return this.userIDBlocked; }
    public void setUserIDBlocked(int userIDBlocked) { this.userIDBlocked = userIDBlocked; }

    @Column(name = "date_block")
    public void setDateBlock(Date dateBlock) { this.dateBlock = dateBlock; }
    public Date getDateBlock() {
        return this.dateBlock;
    }

    @Override
    public String toString() {
        return "BlockedUsers{" +
                "blockID=" + blockID +
                ", userIDMain='" + userIDMain + '\'' +
                ", userIDBlocked='" + userIDBlocked + '\'' +
                ", dateBlock=" + dateBlock +
                '}';
    }
}
