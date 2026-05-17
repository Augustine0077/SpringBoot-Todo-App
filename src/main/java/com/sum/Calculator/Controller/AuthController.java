package com.sum.Calculator.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public class AuthController {
    @PostMapping("/Register")
    public String RegisterUser(@RequestBody Map<String,String>body){

    }
    @PostMapping("/login")
    public String loginUser(@RequestBody Map<String,String>body){

    }
}
