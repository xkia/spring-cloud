package org.xkia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author xkia
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudConsumerApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
