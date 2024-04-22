package grpcgreeting;

import com.proto.dummy.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.IntStream;

public class GreetingClient {

    void sequentialStreamResponse(int range) {
        System.out.println("Hello, I am a gRPC Client.");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
        //old and dummy

//Created a blocking/synchronous greet client
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        //Create a greeting message
        SecureRandom sr = new SecureRandom();

//        //call rpc and get GreetResponse (Protocol buffers)
        List<Integer> a = IntStream.rangeClosed(1, range).boxed().toList();

        a.forEach(n -> System.out.println(greetClient.greet(GreetRequest.newBuilder().setGreeting(Greeting.newBuilder()
                .setFirstName("James")
                .setLastName("Antony")
                .setNum(sr.nextInt(900000))
                .build()).build())));

        System.out.println("Shutdown channel");
        channel.shutdown();
    }

    void parallelStreamResponse(int range) {
        System.out.println("Hello, I am a gRPC Client.");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        //Create a greeting message
        SecureRandom sr = new SecureRandom();
//        //call rpc and get GreetResponse (Protocol buffers)
//        final long startTime = System.nanoTime();
        List<Integer> a = IntStream.rangeClosed(1, range).boxed().toList();


        a.parallelStream().forEach(n -> System.out.println(greetClient.greet(GreetRequest.newBuilder().setGreeting(Greeting.newBuilder()
                .setFirstName("James")
                .setLastName("Antony")
                .setNum(sr.nextInt(900000))
                .build()).build())));

//        final long endTime = System.nanoTime();
//        System.out.println((endTime - startTime) / (double) 1000000000 + "s");
        System.out.println("Shutdown channel");
        channel.shutdown();

    }

    void asynchronousResponse() {
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("perf.txt", true);
        FileOutputStream fos1 = new FileOutputStream("perf1.txt", true);

        GreetingClient gc = new GreetingClient();
//*************************** 1000 Requests ************************************
//        final long startTime = System.nanoTime();
//
//        gc.parallelStreamResponse(1000);
//
//        final long endTime = System.nanoTime();
//        System.out.println((endTime - startTime) / (double) 1000000000 + "s");
//        var c = (endTime - startTime) / (double) 1000000000 + "";
//        byte[] b = c.getBytes();
//        fos.write(b);
//        fos.write(System.getProperty("line.separator").getBytes());


//        final long startTime = System.nanoTime();
//
//        gc.sequentialStreamResponse(1000);
//
//        final long endTime = System.nanoTime();
//        System.out.println((endTime - startTime) / (double) 1000000000 + "s");
//        var c = (endTime - startTime) / (double) 1000000000 + "";
//        byte[] b = c.getBytes();
//        fos1.write(b);
//        fos1.write(System.getProperty("line.separator").getBytes());

        //*************************** 100000 Requests ************************************


        FileOutputStream fos2 = new FileOutputStream("perf2.txt", true);
        FileOutputStream fos3 = new FileOutputStream("perf3.txt", true);


//
//        final long startTime = System.nanoTime();
//
//        gc.parallelStreamResponse(100000);
//
//        final long endTime = System.nanoTime();
//        System.out.println((endTime - startTime) / (double) 1000000000 + "s");
//        var c = (endTime - startTime) / (double) 1000000000 + "";
//        byte[] b = c.getBytes();
//        fos2.write(b);
//        fos2.write(System.getProperty("line.separator").getBytes());


//
//        final long startTime = System.nanoTime();
//
//        gc.sequentialStreamResponse(100000);
//
//        final long endTime = System.nanoTime();
//        System.out.println((endTime - startTime) / (double) 1000000000 + "s");
//        var c = (endTime - startTime) / (double) 1000000000 + "";
//        byte[] b = c.getBytes();
//        fos3.write(b);
//        fos3.write(System.getProperty("line.separator").getBytes());


        //*************************** 1000000 (1M) Requests ************************************
        FileOutputStream fos4 = new FileOutputStream("perf4.txt", true);
        FileOutputStream fos5 = new FileOutputStream("perf5.txt", true);


        final long startTime = System.nanoTime();

        gc.parallelStreamResponse(1000000);

        final long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / (double) 1000000000 + "s");
        var c = (endTime - startTime) / (double) 1000000000 + "";
        byte[] b = c.getBytes();
        fos4.write(b);
        fos4.write(System.getProperty("line.separator").getBytes());


    }
}

