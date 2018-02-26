package ua.vadim.blog.service;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import ua.vadim.blog.GreetingGrpc;
import ua.vadim.blog.HelloRequest;
import ua.vadim.blog.HelloResponse;
import ua.vadim.blog.entity.Blog;

import java.util.LinkedHashSet;

public class GreetingService extends GreetingGrpc.GreetingImplBase {
    private static LinkedHashSet<StreamObserver<HelloResponse>> observers = new LinkedHashSet<>();

    @Autowired
    BlogService blogService;

    @Override
    public StreamObserver<HelloRequest> greeting(StreamObserver<HelloResponse> responseObserver) {
        observers.add(responseObserver);
        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest request) {
                for (StreamObserver<HelloResponse> observer : observers) {
                    observer.onNext(HelloResponse.newBuilder()
                       //     .setMessage(getMessage(request.getTitle()))
                            .setMessage(request.getTitle())
                            .build());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                observers.remove(responseObserver);
            }

            @Override
            public void onCompleted() {
                observers.remove(responseObserver);

            }
        };
    }

    private String getMessage (String title) {
        Blog blog = blogService.getBlogByTitle(title);
        return blog.getTitle() + " | " + blog.getAuthor() + "\n" + blog.getText();
    }

}
