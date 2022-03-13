package ru.iliya;

import java.util.List;

public interface BlockedUsersService {
    void setBlockedUsersByParams(int userID, int userIDBlocked);
    List<ru.iliya.entities.BlockedUsers> findByUserIDBlocked(int userID);
    void deleteBlockedUsersByBlockID(int blockID);
}
