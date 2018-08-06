package org.xkia.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xkia
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudProducerApplication.class, args);
  }
}
