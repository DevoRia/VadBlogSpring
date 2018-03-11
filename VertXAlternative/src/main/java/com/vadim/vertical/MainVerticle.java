package com.vadim.vertical;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.vertx.core.AbstractVerticle;
import org.bson.Document;

public class MainVerticle extends AbstractVerticle {

    MongoClient mongo;

    @Override
    public void start() throws Exception {
        mongo = new MongoClient("localhost" , 27017);
        MongoDatabase db = mongo.getDatabase("blog");
        MongoCollection<Document> collection = db.getCollection("blog");
        FindIterable<Document> documents = collection.find();
        String fullText = " ";

        for (Document document : documents) {
            fullText = fullText + " \n"
                + document.getString("_id")
                + "|"
                + document.getString("author")
                + "\n"
                + document.getString("text");
        }

        String finalFullText = fullText;
        vertx.createHttpServer().requestHandler(req -> {

            req.response()
                .putHeader("content-type", "text/plain")
                .end(finalFullText);
            }).listen(8080);
        System.out.println("HTTP server started on port 8080");
    }

}
