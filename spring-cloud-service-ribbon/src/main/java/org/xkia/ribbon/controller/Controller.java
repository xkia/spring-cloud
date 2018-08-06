package org.xkia.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xkia.ribbon.service.Service;

/**
 * @author xkia
 */
@RestController
public class Controller {

  @Autowired
  Service service;

  @RequestMapping(value = "/hi")
  public String hi(@RequestParam String name){
    return service.hiService(name);
  }


}
