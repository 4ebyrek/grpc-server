package com.eldarbay.grpc.server.demo;

import com.eldarbay.grpc.server.demo.grpc.*;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
public class GrpsServiceImpl extends SuperServiceGrpc.SuperServiceImplBase {


    @Override
    public void ping(Ping request, StreamObserver<Pong> responseObserver) {
        try {
            log.info("message from nestedObject = {}", request.getNestedPing().getMessage());

//        Metadata metadata = new Metadata();
//        Metadata.Key<ErrorResponse> responseKey = ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance());

            Pong.Builder pongBuilder = Pong.newBuilder();

//        ErrorResponse errorResponse = ErrorResponse.newBuilder()
//                .setCode("200")
//                .setMessage("salemAlem")
//                .build();
if(true) {
    throw new MyCustomException("emaeeeee");
}
            responseObserver.onNext(
                    pongBuilder.setSuccessResponse(
                            SuccessResponse.newBuilder()
                                    .setPhraseFromPong("ponggg")
                                    .build()
                    ).build());

//        metadata.put(responseKey, errorResponse);
//        responseObserver.onError(Status.ALREADY_EXISTS.asRuntimeException(metadata));

            responseObserver.onCompleted();
        }catch (Exception e) {

        }
    }
}
