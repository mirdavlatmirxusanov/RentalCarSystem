package com.example.finalproject.model;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private double pricePerDay;
    private String status; // AVAILABLE / RENTED

    public Car() {}

    public Car(int id, String brand, String model, int year, double pricePerDay, String status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.status = status;
    }

    public Car(String brand, String model, int year, double pricePerDay, String status) {
        this(0, brand, model, year, pricePerDay, status);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
