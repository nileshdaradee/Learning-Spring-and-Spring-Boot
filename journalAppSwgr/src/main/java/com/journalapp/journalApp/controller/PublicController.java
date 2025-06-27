package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Tag(name = "Public APIs")
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
