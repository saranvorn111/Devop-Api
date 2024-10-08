package com.example.devopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class DevopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevopApiApplication.class, args);
    }

    @GetMapping("/test")
    public String test(){
        return "verify";
    }
}
