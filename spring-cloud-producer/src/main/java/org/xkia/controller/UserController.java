package org.xkia.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.xkia.entity.User;
import org.xkia.dao.UserRepository;

/**
 * @author xkia
 */
@Slf4j
@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/{id}")
  public User findById(@PathVariable Long id){
    User findOne = this.userRepository.findOne(id);
    log.info(">>>> : ",new Gson().toJson(findOne));
    return findOne;
  }
}
