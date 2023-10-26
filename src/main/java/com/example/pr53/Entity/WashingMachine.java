package com.example.pr53.Entity;

import java.util.Date;

public class WashingMachine
{
    private final String model;
    private final String power;
    private final String width;
    private final String height;
    private final Date created;

    public WashingMachine(String model, String power, String width, String height, Date created){
        this.model=model;
        this.power=power;
        this.width=width;
        this.height=height;
        this.created=created;
    }

    public String getModel() {
        return model;
    }

    public String getPower() {
        return power;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "WashingMachine{" +
                "model='" + model + '\'' +
                ", power='" + power + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", created=" + created +
                '}';
    }
}