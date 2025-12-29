package com.example.finalproject.model;

public class Customer {
    private int id;
    private String fullName;
    private String phone;

    public Customer() {}

    public Customer(int id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }

    public Customer(String fullName, String phone) {
        this(0, fullName, phone);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
