package com.example.pr53.Entity;

import com.example.pr53.Route;

import java.util.ArrayList;
public class Client
{
    //содержат артикулы(номера товара)
    private final ArrayList<Integer> cart = new ArrayList<>();
//    private final ArrayList<Object> orders = null;
    private static int idCounter = 0;
    private final String id;
    private String name;
    private final String email;
    private String password;

    private String role;

    public Client(String name, String email,String password,
                  String role){
        this.role = role;
        this.id = generateId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "USER";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public ArrayList<Integer> getCart() {
        return cart;
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

    public void printCart(){
//         оптимизировать (тк может быть больше 200000 товаров)
        System.out.println(this.cart.size());
        for(int i=0;i<this.cart.size();i++){
            System.out.println(Route.market.getMarketList().get(this.cart.get(i)).getProduct());
        }
    }


    public void deleteFromCart(Integer select) {
        this.cart.remove(this.cart.get(select));
        System.out.println("product deleted");
    }
}