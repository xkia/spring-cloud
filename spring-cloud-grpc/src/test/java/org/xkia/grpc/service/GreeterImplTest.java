package org.xkia.grpc.service;

import static org.junit.Assert.*;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import java.util.logging.Logger;
import org.junit.Test;
import org.xkia.grpc.guide.GreeterGrpc;
import org.xkia.grpc.guide.GreeterOuterClass.HelloReply;
import org.xkia.grpc.guide.GreeterOuterClass.HelloRequest;

public class GreeterImplTest {

  private static final Logger logger = Logger.getLogger(GreeterImplTest.class.getName());

  private ManagedChannel channel;
  private GreeterGrpc.GreeterBlockingStub blockingStub;
  @Test
  public void sayHello() {

    String name = "";
    HelloRequest request = HelloRequest.newBuilder().setName(name).build();
    HelloReply response;
    try {
      response = blockingStub.sayHello(request);
    } catch (StatusRuntimeException e) {
      return;
    }
    try {
      response = blockingStub.sayHelloAgain(request);
    } catch (StatusRuntimeException e) {
      return;
    }
  }

  @Test
  public void sayHelloAgain() {
  }
}