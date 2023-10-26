package com.example.pr53.Entity;

import com.example.pr53.Controller.Route;
import lombok.Getter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Client
{
    //содержат артикулы(номера товара)
    private final ArrayList<Integer> cart = new ArrayList<>();
    private static int idCounter = 0;
    @Getter
    private final String id;
    @Getter
    private String name;

    private final String email;

    private String password;
    private String Role = "USER";
    public Client(String name, String email,String password){
        this.id = generateId();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    private String generateId() {
        idCounter++;
        return String.valueOf(idCounter);
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String pass){
        this.password=pass;
    }

    public String getCart() {
        return printCart();
    }

    public void addToCart(Integer article){
        this.cart.add(article);
        System.out.println("product add");
    }

    @Override
    public String toString() {
        return "Client{" +
                "cart=" + cart +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String printCart(){
//         оптимизировать (тк может быть больше 200000 товаров)
//        System.out.println(this.cart.size());
        ArrayList<String> cartList = new ArrayList<>();
        for(int i=0;i<this.cart.size();i++){
            cartList.add(Route.market.getMarketList().get(this.cart.get(i)).getProduct());
        }
        return cartList.toString();
    }


    public void deleteFromCart(Integer select) {
        this.cart.remove(select);
        System.out.println("product deleted");
    }

    public void createOrder(Integer select){
        Integer article = this.cart.get(select);
        System.out.println(Route.market.getMarketList().get(this.cart.get(article)).getProduct());
    }

    public void setRole(String role) {
        Role = role;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return Role;
    }

    //code код действия для тикета
    public JSONObject sendTicket(Integer code){
        return new JSONObject().put("message", code);
    }
}