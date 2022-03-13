package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.BlockedUsers;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class BlockedUsersServiceImpl implements BlockedUsersService {
    @Autowired
    BaseRepository baseRepository;

    @Override
    public List<BlockedUsers> findByUserIDBlocked(int userID) {
        return baseRepository.findByUserIDBlocked(userID);
    }

    @Override
    public void setBlockedUsersByParams(int userID, int userIDBlocked) {
        baseRepository.setBlockedUsersByParams(userID, userIDBlocked);
    }

    @Override
    public void deleteBlockedUsersByBlockID(int blockID) {
        baseRepository.deleteBlockedUsersByBlockID(blockID);
    }
}
