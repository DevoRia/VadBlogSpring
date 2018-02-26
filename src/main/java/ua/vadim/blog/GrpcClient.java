package ua.vadim.blog;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8085)
                .usePlaintext(true)
                .build();
        GreetingGrpc.GreetingStub stub = GreetingGrpc.newStub(channel);
        StreamObserver<HelloRequest> server = stub.greeting(new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse helloResponse) {
                System.out.println("Hello: " + helloResponse.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });

        server.onNext(HelloRequest.newBuilder()
                .setTitle("Титулка")
                .build());
    }
}
