package org.xkia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xkia
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
public class SpringCloudProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudProducerApplication.class, args);
  }
}
