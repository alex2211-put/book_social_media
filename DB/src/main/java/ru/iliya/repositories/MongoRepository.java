package ru.iliya.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.Document;
import com.mongodb.MongoClient;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Message;

import java.util.List;

public interface MongoRepository {
    MongoClient getClient();
    void insertMessage(Message message) throws JsonProcessingException;
    List<Document> getMessagesForCollection(String collection);
    void addDialogsToMongoDB(String userFrom, String userTo) throws JSONException;
    List<Document> getDialogsForCollection(String collection);
    String getLastMessagesForCollection(String collection);
}