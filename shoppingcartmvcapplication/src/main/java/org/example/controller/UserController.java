package org.example.controller;

import org.example.DTO.UserDto;
import org.example.Exception.UserNotFoundException;
import org.example.Exception.ValidationException;
import org.example.Handler.UserHandler;
import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserHandler userHandler;

    @GetMapping("/adduser")
    public String addUser(){
        return "adduser";
    }

    @PostMapping(value = "/adduser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user)  {
      try {
          userHandler.validateUser(user);
          return ResponseEntity.ok(userHandler.addUser(user));
      }catch (ValidationException e){
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
      }catch (RuntimeException e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
    }

    @GetMapping(value = "/displayUserDteail")
    public ResponseEntity<?> displayUserDteail(@RequestHeader("userId") int userId) {
        try {
            LOGGER.info("Class :: UserController :: method :: displayUserDteail :: userId ::" +userId);
            UserDto userDto = userHandler.displayUser(userId);
            LOGGER.info("User details displayed successfully for userId: " +userId);
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            LOGGER.warn("User not found for userId " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        } catch (Exception e) {
            LOGGER.error("Internal server error while processing userId: {}", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
