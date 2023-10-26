package com.example.pr53;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
//@ComponentScan(basePackages = {''})
public class Pr53Application {

    public static void main(String[] args) {
        SpringApplication.run(Pr53Application.class, args);
    }
}
