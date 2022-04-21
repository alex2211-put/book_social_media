package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.BlockedUsers;
import ru.iliya.repositories.BlockedUsersRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class BlockedUsersBaseServiceImpl implements BlockedUsersBaseService{

    @Autowired
    private BlockedUsersRepository blockedUsersRepository;

    @Override
    public void setBlockedUsersByUserIdAndUserIdBlocked(int userID, int userIDBlocked) {
        BlockedUsers blockedUsers = new BlockedUsers();
        blockedUsers.setUserIDBlocked(userIDBlocked);
        blockedUsers.setUserID(userID);
        Date current = new Date();
        blockedUsers.setDateBlock(current);
        blockedUsersRepository.save(blockedUsers);
    }

    @Override
    public List<BlockedUsers> findByUserIDBlocked(int userIDBlocked) {
        return blockedUsersRepository.findByUserIDBlocked(userIDBlocked);
    }

    @Override
    @Transactional
    public void deleteBlockedUsersByBlockID(int blockID) {
        blockedUsersRepository.deleteBlockedUsersByBlockID(blockID);
    }
}
