package ru.iliya.helpers;

import ru.iliya.entities.User;

public class LastMessage {
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
}
