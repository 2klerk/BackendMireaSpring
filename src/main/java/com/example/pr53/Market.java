package com.example.pr53;

import com.example.pr53.Entity.Book;
import com.example.pr53.Entity.Product;
import com.example.pr53.Entity.Telephone;

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

    public HashMap<Integer, Product> getMarketList() {
        return MarketList;
    }
}
