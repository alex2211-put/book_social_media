package ru.iliya.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.iliya.entities.User;
import ru.iliya.services.MessageServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class LastMessage {
    @Autowired
    private static MessageServiceImpl messageService;

    public User user;
    public String message;

    @Override
    public String toString() {
        return "LastMessage{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }

    public String ownerId;

    public LastMessage(User user, String message, String ownerId) {
        this.user = user;
        this.message = message;
        this.ownerId = ownerId;
    }

    public static List<LastMessage> getLastMessagesList(List<User> users, String userId) {
        List<LastMessage> lastMessages = new ArrayList<>();
        for (User user1 : users) {
            String message = messageService.getLastMessage(userId, user1);
            LastMessage lastMessage = new LastMessage(user1, message, userId);
            lastMessages.add(lastMessage);
        }
        return lastMessages;
    }
}
