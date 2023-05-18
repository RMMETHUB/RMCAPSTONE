package com.capstoneproject.ms3authenticationservicev1.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }
}
