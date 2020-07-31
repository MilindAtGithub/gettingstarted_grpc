package com.milind.grpc.server;

import com.milind.grpc.ConfirmedLineItems;
import com.milind.grpc.Order;
import com.milind.grpc.OrderConfirmation;
import com.milind.grpc.OrderConfrimationServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.Random;

public class OrderConfirmationServiceImpl extends
        OrderConfrimationServiceGrpc.OrderConfrimationServiceImplBase {

    @Override
    public void confrim(Order request,
                        StreamObserver<OrderConfirmation> responseObserver){

        System.out.println("Received Order: "+ request);
        int val = new Random().nextInt(3);
        OrderConfirmation.Builder orderbuilder = OrderConfirmation.newBuilder()
                .setOrderId(request.getOrderId());
        if (val <= 1) {
            ConfirmedLineItems l1 = ConfirmedLineItems.newBuilder()
                    .setConfirmQuantity(2)
                    .setSku(request.getLineItemsList().get(0).getSku())
                    .build();
            ConfirmedLineItems l2 = ConfirmedLineItems.newBuilder()
                    .setConfirmQuantity(5)
                    .setSku(request.getLineItemsList().get(2).getSku())
                    .build();
            orderbuilder.addAllConfirmedLineItems(Arrays.asList(l1,l2));
        } else {
            ConfirmedLineItems l1 = ConfirmedLineItems.newBuilder()
                    .setConfirmQuantity(1)
                    .setSku(request.getLineItemsList().get(1).getSku())
                    .build();
            orderbuilder.addConfirmedLineItems(l1);
        }
        responseObserver.onNext(orderbuilder.build());
        responseObserver.onCompleted();
    }

}
