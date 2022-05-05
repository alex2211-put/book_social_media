package ru.iliya.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Message;
import ru.iliya.entities.User;
import ru.iliya.repositories.MongoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MongoRepository mongoRepository;
    @Autowired
    UserBaseService userBaseService;

    @Override
    public List<User> getDialogsForUser(String owner) {
        List<Document> documents = mongoRepository.getDialogsForCollection(owner);
        System.out.println(docsToStrings(documents));
        List<User> users = new ArrayList<>();
        for (Document document : documents) {
            String[] user_ids = document.get("user").toString().replaceAll("[ \\[\\]]", "").split(",");
            for (String user_id : user_ids) {
                User user = userBaseService.findUserByID(Integer.parseInt(user_id));
                users.add(user);
            }
        }
        return users;
    }

    @Override
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

    @Override
    public List<Document> getAllMessagesForDialog(String owner, String partner) {
        String id;
        int i = partner.compareTo(owner);
        if (i < 0) {
            id = partner + '_' + owner;
        } else {
            id = owner + '_' + partner;
        }
        return mongoRepository.getMessagesForCollection(id);
    }

    private ArrayList<String> docsToStrings(List<Document> documents) {
        ArrayList<String> docStrings = new ArrayList<>();
        documents.forEach((Document doc) -> docStrings.add(doc.toString()));
        return docStrings;
    }

    @Override
    public String getLastMessage(String userId, User user1) {
        String id;
        String partner = String.valueOf(user1.getUserID());
        int i = partner.compareTo(userId);
        if (i < 0) {
            id = partner + '_' + userId;
        } else {
            id = userId + '_' + partner;
        }
        return mongoRepository.getLastMessagesForCollection(id);
    }

    @Override
    public List<Message> getMessagesForPersons(String person, String owner) {
        List<Document> messagesDocs = getAllMessagesForDialog(owner, person);
        List<Message> messages = new ArrayList<>();
        for (Document document : messagesDocs) {
            messages.add(new Message(
                    document.get("text").toString(),
                    document.get("from").toString(),
                    document.get("to").toString(),
                    "2022"));
        }
        return messages;
    }
}
