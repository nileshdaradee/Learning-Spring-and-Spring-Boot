package com.nilesh.WebApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet()
    {
        return "Welcome Nilesh";
    }

    @RequestMapping("/about")
    public String about()
    {
        return "Developed by Nilesh all rights reserved";
    }
}
