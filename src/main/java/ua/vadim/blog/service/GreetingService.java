package ua.vadim.blog.service;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vadim.blog.GreetingGrpc;
import ua.vadim.blog.HelloRequest;
import ua.vadim.blog.HelloResponse;
import ua.vadim.blog.entity.Blog;

@Service
public class GreetingService extends GreetingGrpc.GreetingImplBase {

    @Autowired
    private BlogService blogService;

    @Override
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("GRPC Request: " + request.getTitle());
        //Функції callback
        responseObserver.onNext(HelloResponse.newBuilder()
                .setMessage(getMessage(request.getTitle()))
                .build());
        //Обов'язково використовуємо метод onCompleted щоб програма не зациклювалась на методі onNext
        responseObserver.onCompleted();
    }

    private String getMessage (String title) {
        //Дістаємо автора та текст за заголовком з бази
        Blog blog;
        try {
            blog = this.blogService.getBlogByTitle(title);
        } catch (NullPointerException e){
            return "Нічого не знайдено";
        }
        return blog.getTitle() + " | " + blog.getAuthor() + "\n" + blog.getText();
    }

}
