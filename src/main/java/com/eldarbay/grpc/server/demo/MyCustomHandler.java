package com.eldarbay.grpc.server.demo;

import com.eldarbay.grpc.server.demo.grpc.ErrorResponse;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import org.lognet.springboot.grpc.recovery.GRpcExceptionHandler;
import org.lognet.springboot.grpc.recovery.GRpcExceptionScope;
import org.lognet.springboot.grpc.recovery.GRpcServiceAdvice;

@GRpcServiceAdvice
public class MyCustomHandler {
    @GRpcExceptionHandler
    public Status handle (MyCustomException exc, GRpcExceptionScope scope){
        Metadata metadata = new Metadata();
        Metadata.Key<ErrorResponse> responseKey = ProtoUtils.keyForProto(ErrorResponse.getDefaultInstance());

        ErrorResponse errorResponse = ErrorResponse.newBuilder()
                .setCode("200")
                .setMessage("salemAlem")
                .build();
        metadata.put(responseKey, errorResponse);

        return Status.DATA_LOSS.withDescription(exc.getMessage());
    }
}
