package ru.iliya.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.operation.OrderBy;
import org.bson.Document;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Message;

import java.util.*;

@Repository
public class MongoRepositoryImpl implements MongoRepository {
    MongoClient mongoClient;

    @Override
    public MongoClient getClient() {
        if (mongoClient == null) {
            mongoClient = new MongoClient("localhost", 27017);
        }
        return mongoClient;
    }

    private MongoDatabase getDb(String name) {
        return getClient().getDatabase(name);
    }

    public MongoRepositoryImpl() {
        this.mongoClient = new MongoClient("localhost", 27017);
    }

    @Override
    public void insertMessage(Message message) {
        MongoDatabase db = getDb("messages");
        MongoCollection<Document> collection1 = db.getCollection(message.getId());
        collection1.insertOne(Document.parse(message.toJson()));
    }


    @Override
    public List<Document> getMessagesForCollection(String collection) {
        MongoDatabase db = getDb("messages");
        return getDocuments(collection, db);
    }

    private List<Document> getDocuments(String collection, MongoDatabase db) {
        MongoCollection<Document> collection1 = db.getCollection(collection);
        FindIterable<Document> iterDoc = collection1.find();
        List<Document> documents = new ArrayList<>();
        for (Document document : iterDoc) {
            documents.add(document);
        }
        return documents;
    }

    @Override
    public void addDialogsToMongoDB(String owner, String partner) throws JSONException {
        addDialogToMongoDB(owner, partner);
        addDialogToMongoDB(partner, owner);
    }

    private void addDialogToMongoDB(String owner, String partner) throws JSONException {
        MongoDatabase db = getDb("dialogs");
        MongoCollection<Document> collection1 = db.getCollection(owner);
        FindIterable<Document> iterDoc = collection1.find();
        Document document = null;
        for (Document documentColl : iterDoc) {
            document = documentColl;
        }
        HashSet<String> userDoc;
        if (document != null) {
            userDoc = new HashSet<>(Arrays.asList(document.get("user").toString().replace("[", "").replace(" ", "").replace("]", "").split(",")));
            userDoc.add(partner);
            collection1.updateOne(document, new BasicDBObject("$set", Document.parse(hashSetToJson(userDoc))));
        } else {
            collection1.insertOne(Document.parse("{'user': '" + partner + "'}"));
        }
    }

    private String hashSetToJson(HashSet<String> hashSet) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("user", hashSet);
        return json.toString();
    }

    @Override
    public List<Document> getDialogsForCollection(String collection) {
        MongoDatabase db = getDb("dialogs");
        return getDocuments(collection, db);
    }

    @Override
    public String getLastMessagesForCollection(String collection) {
        MongoDatabase db = getDb("messages");
        MongoCollection<Document> collection1 = db.getCollection(collection);
        FindIterable<Document> cursor = collection1.find().sort(new BasicDBObject("_id", OrderBy.DESC.getIntRepresentation())).limit(1);
        for (Document document : cursor) {
            return (String) document.get("text");
        }
        return "";
    }

}
