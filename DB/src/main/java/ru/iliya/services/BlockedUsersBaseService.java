package ru.iliya.services;

import ru.iliya.entities.BlockedUsers;

import java.util.List;

public interface BlockedUsersBaseService {

    void deleteBlockedUsersByBlockID(int blockID);

    List<BlockedUsers> findByUserIDBlocked(int userIDBlocked);

    void setBlockedUsersByParams(int userID, int userIDBlocked);
}