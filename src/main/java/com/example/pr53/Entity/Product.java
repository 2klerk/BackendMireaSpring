package com.example.pr53.Entity;
import lombok.Getter;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {
    @Getter
    private Boolean order;
    @Getter
    private String type;
    private Object product;
    public Product(String type, Object product){
        this.type=type;
        this.product=product;
        this.order = false;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProduct(Object product) {
        this.product = product;
    }
    public String getProduct() {
        return product.toString();
    }

}
