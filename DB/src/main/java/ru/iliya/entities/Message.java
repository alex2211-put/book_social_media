package ru.iliya.entities;

import org.springframework.data.annotation.Id;


public class Message {

    @Id
    private String id;

    private String message;
    private String from;
    private String to;
    private String date;

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Message(String message, String from, String to, String date) {
        this.message = message;
        this.from = from;
        this.to = to;
        this.date = date;
        int i = from.compareTo(to);
        if (i < 0) {
            id = from + '_' + to;
        } else {
            id = to + '_' + from;
        }
    }

    public Message(){
    };

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String toJson() {
        return "{'text': '" + this.message +
                "', 'from': '" + this.from +
                "', 'to': '" + this.to + "'}";
    }
}