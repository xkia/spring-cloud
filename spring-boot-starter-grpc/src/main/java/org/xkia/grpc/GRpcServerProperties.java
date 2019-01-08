package org.xkia.grpc;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xkia
 * @data 2018/11/18
 */
@ConfigurationProperties("grpc")
public class GRpcServerProperties {

  private int port = 6565;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
