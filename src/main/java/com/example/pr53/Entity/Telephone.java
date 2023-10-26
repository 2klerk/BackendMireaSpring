package com.example.pr53.Entity;

public class Telephone {
    private final String RAM;
    private final String model;

    private final String CPU;
    private final String GPU;

    public Telephone(
            String RAM, String model, String CPU, String GPU
    ){
        this.RAM = RAM;
        this.model = model;
        this.CPU = CPU;
        this.GPU = GPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getModel() {
        return model;
    }

    public String getCPU() {
        return CPU;
    }

    public String getGPU() {
        return GPU;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "RAM='" + RAM + '\'' +
                ", model='" + model + '\'' +
                ", CPU='" + CPU + '\'' +
                ", GPU='" + GPU + '\'' +
                '}';
    }
}
