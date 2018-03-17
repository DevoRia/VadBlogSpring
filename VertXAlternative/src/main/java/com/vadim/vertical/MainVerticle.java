package com.vadim.vertical;
import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

    private final String DATABASE = "blog";
    private final String COLLECTION = DATABASE;

    @Override
    public void start() throws Exception {

        String posts = new PostsBuilder(new DataDao(DATABASE, COLLECTION)).getPosts();

        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                .putHeader("content-type", "text/plain")
                .end(posts);
            }).listen(8080);
        System.out.println("HTTP server started on port 8080");
    }

}
