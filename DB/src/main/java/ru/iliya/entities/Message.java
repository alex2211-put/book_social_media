package ru.iliya.entities;

import org.springframework.data.annotation.Id;


public class Message {

    @Id
    public String id;

    public String message;
    public String user1id;
    public String user2id;

    public Message(){

    };


    public Message(String user1id, String user2id, String message) {
        int i = user1id.compareTo(user2id);
        if (i < 0) {
            id = user1id + '_' + user2id;
        } else {
            id = user2id + '_' + user1id;
        }
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}