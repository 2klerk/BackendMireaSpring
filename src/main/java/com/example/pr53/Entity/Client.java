package com.example.pr53.Entity;

import com.example.pr53.Controller.Route;
import lombok.Getter;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Client
{

    //содержат артикулы(номера товара)
    private final ArrayList<Integer> cart = new ArrayList<>();
    private final ArrayList<String>order=new ArrayList<>();
    private final ArrayList<LocalDate>order_time_end=new ArrayList<>();
    private final ArrayList<String>order_time_final=new ArrayList<>();
    private final ArrayList<Integer>order_code=new ArrayList<>();
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
    public Client(String name, String email,String password,String Role){
        this.id = generateId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.Role = Role;
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
    public String getOrder() {
        return order.toString();
    }
    public void addToCart(Integer article){
        this.cart.add(article);
        System.out.println("product add");
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
        order.add(Route.market.getMarketList().get(article).getProduct().toString());
        this.order_code.add(this.generateOrderCode());
        this.order_time_end.add(LocalDate.now().plusDays((int) (Math.random() * 10)));
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

    private Integer generateOrderCode() {
        return (int) (Math.random() * 10);
    }

    public String getOrder_time(LocalDate dt) {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), dt);
        return MessageFormat.format("Заказа будет доставлен через {0}", daysLeft);
    }
    public ArrayList<LocalDate> getOrder_time_end() {
        return order_time_end;
    }

    public ArrayList<String> getOrder_time_final() {
        for(int i=0;i<order_time_end.size();i++){
            order_time_final.add(getOrder_time(order_time_end.get(i)));
        }
        return order_time_final;
    }

    public ArrayList<Integer> getOrder_code() {
        return order_code;
    }
}