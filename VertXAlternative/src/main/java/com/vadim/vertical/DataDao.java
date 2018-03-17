package com.vadim.vertical;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class DataDao {

    private final String HOST = "localhost";
    private final int PORT = 27017;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private FindIterable<Document> documents;

    DataDao (String databaseName, String collectionName) {
        mongoClient = new MongoClient(HOST, PORT);
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

    public FindIterable<Document> getDocuments () {
        return documents = this.collection.find();
    }

}
