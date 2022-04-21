package ru.iliya.services;

import ru.iliya.entities.BlockedUsers;

import java.util.List;

public interface BlockedUsersService {

    void setBlockedUsersByParams(int userID, int userIDBlocked);

    List<BlockedUsers> findByUserIDBlocked(int userID);

    void deleteBlockedUsersByBlockID(int blockID);
}
