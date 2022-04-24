package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.BlockedUsers;

import java.util.List;

@Service
public class BlockedUsersServiceImpl implements BlockedUsersService {

    @Autowired
    BlockedUsersDataBaseService blockedUsersDataBaseService;

    @Override
    public List<BlockedUsers> findByUserIDBlocked(int userID) {
        return blockedUsersDataBaseService.findByUserIDBlocked(userID);
    }

    @Override
    public void setBlockedUsersByParams(int userID, int userIDBlocked) {
        blockedUsersDataBaseService.setBlockedUsersByUserIdAndUserIdBlocked(userID, userIDBlocked);
    }

    @Override
    public void deleteBlockedUsersByBlockID(int blockID) {
        blockedUsersDataBaseService.deleteBlockedUsersByBlockID(blockID);
    }
}
