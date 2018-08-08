package org.xkia.ribbon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author xkia
 */
@org.springframework.stereotype.Service
public class Service {

  @Autowired
  RestTemplate restTemplate;

  public String hiService(String name) {
    return restTemplate.getForObject("http://PRODUCER/hi?name=" + name, String.class);
  }

}
