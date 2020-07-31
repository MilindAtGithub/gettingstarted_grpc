package com.milind.grpc.client;

import com.milind.grpc.OrderConfirmation;
import com.milind.grpc.OrderConfrimationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * This is the GRPC Client to send the request and response.
 */
public class GrpcClient {


    public static void main(String[] args) throws InterruptedException {
        int port = args.length == 0 ? 8080 : Integer.parseInt(args[0]);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();

        OrderConfrimationServiceGrpc.OrderConfrimationServiceBlockingStub stub =
            OrderConfrimationServiceGrpc.newBlockingStub(channel);

        OrderConfirmation orderConfirmation = stub.confrim(DummyObjectCreator.createOrder());

        System.out.println(orderConfirmation);
    }
}
