package org.xkia.grpc.service;

import io.grpc.stub.StreamObserver;
import org.xkia.grpc.guide.GreeterGrpc;
import org.xkia.grpc.guide.GreeterOuterClass.HelloReply;
import org.xkia.grpc.guide.GreeterOuterClass.HelloRequest;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase
{

  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    HelloReply build = HelloReply.newBuilder().setMessage("hello world").build();
    responseObserver.onNext(build);
    responseObserver.onCompleted();
  }

  @Override
  public void sayHelloAgain(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    HelloReply reply = HelloReply.newBuilder().setMessage("Hello again " + request.getName()).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
