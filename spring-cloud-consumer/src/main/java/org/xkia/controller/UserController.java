package org.xkia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xkia
 */
@Slf4j
@RestController
public class UserController {


  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  DiscoveryClient discoveryClient;

  @GetMapping(value = "/user/{id}")
  public String get(@PathVariable Long id){
    log.info(">>>>>>>> get id : {}" ,id);
    log.info(">>>>>> client : {}",discoveryClient.getServices());
    return this.restTemplate.getForObject("http://localhost:8080/" + id, String.class);
  }


}
