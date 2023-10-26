package com.example.pr53;

import com.example.pr53.Entity.Book;
import com.example.pr53.Entity.Telephone;
import com.example.pr53.Entity.WashingMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestRoute {
    @GetMapping("/Telephone")
    public Telephone getTelephoneObj() {
        return new Telephone("8gb", "pixel7", "tensor g2","mali");
    }
    @GetMapping("/Book")
    public Book getBook() {
        return new Book(
                "Мёртвые души",
                new Date(1903-10-10),
                "Gogol",
                "Chichikov"
        );
    }
    @GetMapping("/WashingMachine")
    public WashingMachine getWashingMachine() {
        return new WashingMachine(
                "bibi",
                "2wt",
                "90cm",
                "100cm",
                new Date(2017-10-10)
        );
    }
}
