package com.example.finalproject.model;

public class Rental {
    private int id;
    private int carId;
    private int customerId;
    private String startDate; // yyyy-mm-dd
    private String endDate;   // yyyy-mm-dd
    private double total;

    public Rental() {}

    public Rental(int id, int carId, int customerId, String startDate, String endDate, double total) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.total = total;
    }

    public Rental(int carId, int customerId, String startDate, String endDate, double total) {
        this(0, carId, customerId, startDate, endDate, total);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
