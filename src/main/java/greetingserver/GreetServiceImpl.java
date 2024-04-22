package greetingserver;

import com.proto.dummy.GreetRequest;
import com.proto.dummy.GreetResponse;
import com.proto.dummy.GreetServiceGrpc;
import com.proto.dummy.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();

        long sum = greeting.getNum() + greeting.getNum() + 1;
        String result = "hello " + firstName + " requested sum: " + sum;

        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();
        //send response
        responseObserver.onNext(response);

        //Complete rpc call
        responseObserver.onCompleted();
    }
}
