package com.example.pr53.Entity;

import java.util.Date;

public class Book
{
    private final String text;
    private final String author;
    private final String name;
    private final Date old;

    public Book(String name, Date old, String author, String text){
        this.name = name;
        this.author = author;
        this.old = old;
        this.text = text;
    }

    public Date getOld() {
        return old;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Book{" +
                "text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", old=" + old +
                '}';
    }
}