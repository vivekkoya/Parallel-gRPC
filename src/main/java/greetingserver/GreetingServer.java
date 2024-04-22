package greetingserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello gRPC");
//Build server
        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .build();
        //Start server
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
        }));
//        server.shutdown();
//        System.out.println("Successfully shutdown server");
        server.awaitTermination();
    }
}
