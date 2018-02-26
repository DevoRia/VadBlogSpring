package ua.vadim.blog.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class GrpcServer  {

    @PostConstruct
    public void serverStarting (){
        Thread thread = new Thread(() -> {
            Server server = ServerBuilder.forPort(8085)
                    .addService(new GreetingService())
                    .build();
            try {
                server.start();
                server.awaitTermination();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

    }

}
