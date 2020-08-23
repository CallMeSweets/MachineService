package com.configuration.machine.controllers;

import com.configuration.machine.models.User;
import com.configuration.machine.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){
        userService.createUser(user);
        log.trace("New product created, name: " + user.getUsername());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/{username}")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.loadUserByUsername(username));
    }



}
