package com.vadim.vertical;

import org.bson.Document;

public class PostsBuilder {

    private DataDao dao;

    public PostsBuilder (DataDao dao) {
        this.dao = dao;
    }

    String getPosts () {
        String fullText = null;
        for (Document document : dao.getDocuments()) {
            fullText = fullText + " \n"
                + document.getString("_id")
                + "|"
                + document.getString("author")
                + "\n"
                + document.getString("text");
        }
        return fullText;
    }

}
