package com.example.pr53.Entity;
import lombok.Getter;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {
    @Getter
    private Boolean order;
    private Integer order_code;
    @Getter
    private LocalDate order_time;
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
    private Integer generateOrderCode(){
        return (int) 10;
    }
    public void CreateOrder(){
        this.order_code=this.generateOrderCode();
        this.order=true;
        this.order_time = LocalDate.now().plusDays((int) (Math.random()*10));

    }
    public String getOrderTime(){
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), this.order_time);
        return MessageFormat.format("Заказа будет доставлен через {0}",daysLeft);
    }
    public String getProduct() {
        return product.toString();
    }

}
