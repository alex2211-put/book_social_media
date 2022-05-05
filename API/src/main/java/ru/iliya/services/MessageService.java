package ru.iliya.services;

import org.bson.Document;
import ru.iliya.entities.Message;
import ru.iliya.entities.User;

import java.util.ArrayList;
import java.util.List;

public interface MessageService {

    List<User> getDialogsForUser(String owner);

    void writeToUser(Message message);

    List<Document> getAllMessagesForDialog(String owner, String partner);

    private ArrayList<String> docsToStrings(List<Document> documents) {
        ArrayList<String> docStrings = new ArrayList<>();
        documents.forEach((Document doc) -> docStrings.add(doc.toString()));
        return docStrings;
    }

    String getLastMessage(String userId, User user1);

    List<Message> getMessagesForPersons(String person, String owner);
}
