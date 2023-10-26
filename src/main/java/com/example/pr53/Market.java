package com.example.pr53;

import com.example.pr53.Entity.Book;
import com.example.pr53.Entity.Product;
import com.example.pr53.Entity.Telephone;
import com.example.pr53.Entity.WashingMachine;

import java.util.Date;
import java.util.HashMap;

public class Market {
    public static HashMap<Integer, Product>MarketList=new HashMap<>();
    public Market(){
        createMarket();
    }
    private void createMarket(){
        MarketList.put(
                1,new Product("Book",
                new Book("Война и мир", new Date(1812-12-12),"Толстой","Книга об описании дуба")));
        MarketList.put(
                2,new Product("Book",
                        new Book("Божественная комедия", new Date(1850-12-12),"-","Класс сущности и 9 кругов ада")));
        MarketList.put(
                3,new Product("Book",
                        new Book("Ганнибал лектор", new Date(1800-10-10),"-","-")));
        MarketList.put(
                4,new Product("Telephone",
                        new Telephone("8gb","pixel7","tensorg2","mali")));
    }
    public void createBook(String name, Date date, String author, String text){
        Product pr = new Product("Book", new Book(name, date,author,text));
        MarketList.put(pr.hashCode(),pr);
    }
    public void createTelephone(String model, String ram, String cpu, String gpu){
        Product pr = new Product("Telephone", new Telephone(ram, model,cpu,gpu));
        MarketList.put(pr.hashCode(),pr);
    }
    public void createWashingMachine(String model, Date date, String power, String width, String height){
        Product pr = new Product("Washing machine", new WashingMachine(model, power, width,height, date));
        MarketList.put(pr.hashCode(),pr);
    }
    public HashMap<Integer, Product> getMarketList() {
        return MarketList;
    }
}
