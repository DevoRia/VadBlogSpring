package ua.vadim.blog.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class GrpcServer  {

    @Autowired
    private GreetingService greetingService;

    @PostConstruct
    public void serverStarting (){
        //Відкриваємо сервер в новому потоці, так як він використовує вічний цикл
        Thread thread = new Thread(() -> {
            Server server = ServerBuilder.forPort(8085)
                    .addService(greetingService)
                    .build();
            try {
                System.out.println("START gRPC SERVER...");
                server.start();
                server.awaitTermination();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

    }

}
