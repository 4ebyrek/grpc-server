package com.eldarbay.grpc.server.demo;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;

@Slf4j
@GRpcGlobalInterceptor
public class GLobalGrpcInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        ServerCall<ReqT, RespT> listener = new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {

            @Override
            public void sendMessage(RespT message) {
                log.debug("Sending message to cliens: {}",  message);
                super.sendMessage(message);
            }
        };

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(
                next.startCall(listener, headers)
        ) {

            @Override
            public void onMessage(ReqT message) {
                log.debug("Received message from cliens: {}", message);
                super.onMessage(message);
            }

        };
    }
}
