package ru.iliya.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Message;
import ru.iliya.repositories.MongoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MongoRepository mongoRepository;

    public ArrayList<String> getDialogsForUser(String owner) {
        List<Document> documents = mongoRepository.getDialogsForCollection(owner);
        return docsToStrings(documents);
    }

    public void writeToUser(Message message) {
        try {
            mongoRepository.insertMessage(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();  // кидаем ошибку
        }
        try {
            mongoRepository.addDialogsToMongoDB(message.getFrom(), message.getTo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllMessagesForDialog(String owner, String partner) {
        String id;
        int i = partner.compareTo(owner);
        if (i < 0) {
            id = partner + '_' + owner;
        } else {
            id = owner + '_' + partner;
        }
        List<Document> documents = mongoRepository.getMessagesForCollection(id);
        return docsToStrings(documents);
    }

    private ArrayList<String> docsToStrings(List<Document> documents) {
        ArrayList<String> docStrings = new ArrayList<>();
        documents.forEach((Document doc) -> docStrings.add(doc.toString()));
        return docStrings;
    }
}
