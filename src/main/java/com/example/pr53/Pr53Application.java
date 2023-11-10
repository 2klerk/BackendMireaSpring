package com.example.pr53;

import com.example.pr53.Controller.Route;
import com.example.pr53.Entity.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {''})
public class Pr53Application {

    public static void main(String[] args) {
        Route.clients.put("admin@gmail.com", new Client("Admin", "admin@gmail.com","admin","ADMIN"));
        SpringApplication.run(Pr53Application.class, args);
    }
}
