package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "OK";
    }



    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        return userService.saveNewUser(user);
    }

}
