package com.eldarbay.grpc.server.demo;

public class MyCustomException extends RuntimeException{
    public MyCustomException(String message) {
        super(message);
    }
}
