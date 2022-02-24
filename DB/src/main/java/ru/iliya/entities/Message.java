package ru.iliya.entities;

import org.springframework.data.annotation.Id;


public class Message {

    @Id
    public String id;

    public String message;
    public String from;
    public String to;

    public Message(){

    };


    public Message(String from, String to, String message) {
        int i = from.compareTo(to);
        if (i < 0) {
            id = from + '_' + to;
        } else {
            id = to + '_' + from;
        }
        this.message = message;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}