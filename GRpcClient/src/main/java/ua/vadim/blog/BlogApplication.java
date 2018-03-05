package ua.vadim.blog;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class BlogApplication {

	@PostConstruct
	public void client () throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//reader для зчитування тексту
		ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085")
				.usePlaintext(true)
				.build();

		GreetingGrpc.GreetingBlockingStub stub = GreetingGrpc.newBlockingStub(channel);//робимо канал синхронним
		while (true) {
			String request = reader.readLine();
			if ("STOP".equals(request)) break;// Зупиняємо програму, якщо відправимо STOP
			HelloResponse response = stub.greeting(HelloRequest.newBuilder()
					.setTitle(request)
					.build());

			System.out.println(response.getMessage());
		}
	}

	public static void main(String[] args)  {
		SpringApplication.run(BlogApplication.class, args);
	}
}
