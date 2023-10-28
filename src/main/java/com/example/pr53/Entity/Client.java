package com.example.pr53.Entity;

import com.example.pr53.Controller.Route;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Date;

public class Client
{
    private static final String SECRET_KEY = "secretKey";
    //содержат артикулы(номера товара)
    private final ArrayList<Integer> cart = new ArrayList<>();
//    private final ArrayList<Object> orders = null;
    private static int idCounter = 0;
    private final String id;
    private String name;
    private final String email;
    private String password;
    public Client(String name, String email,String password){
        this.id = generateId();
        this.name = name;
        this.email = email;
        this.password = password;
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

    public static String generateToken(String subject, long ttlMillis) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ttlMillis);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getSubjectFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}