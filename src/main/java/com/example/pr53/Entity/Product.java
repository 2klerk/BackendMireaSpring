package com.example.pr53.Entity;
import java.util.*;
public class Product {
    private String type;
    private Object product;
    public Product(String type, Object product){
        this.type=type;
        this.product=product;
    }

    public String getType() {
        return type;
    }

    public Object getProduct() {
        return product.toString();
    }

}
