package com.manideep.journalApp.controllers;

import com.manideep.journalApp.entities.User;
import com.manideep.journalApp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);
    @PostMapping("createUser")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            user.setRoles(Arrays.asList("USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/helloUser")
    public String helloUser(){

        logger.info("Logger got invoked");
        logger.info("I want to become Rich");
        logger.error("I want to become an Playboy");
        logger.info("I wanna become crazy ass rich playboy");

        return "Hi brother you would be single forever but you enjoy a looooot";
    }

}
