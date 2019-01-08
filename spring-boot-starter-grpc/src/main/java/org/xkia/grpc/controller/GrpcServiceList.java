package org.xkia.grpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.xkia.grpc.GRpcServerRunner;
import org.xkia.grpc.annotation.GRpcService;

/**
 * @author xkia
 * @data 2018/11/18
 */
@Controller
@ConditionalOnBean(annotation = GRpcService.class)
public class GrpcServiceList {

  @Autowired
  GRpcServerRunner gRpcServerRunner;

  public Object getServiceList() {
    if (gRpcServerRunner != null) {
      return null;
    }
    return null;
  }
}
