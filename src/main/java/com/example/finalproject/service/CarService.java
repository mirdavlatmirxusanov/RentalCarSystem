package com.example.finalproject.service;

import com.example.finalproject.model.Car;
import com.example.finalproject.repository.CarRepository;

import java.util.List;

public class CarService {
    private final CarRepository repo = new CarRepository();

    public List<Car> getAll() {
        return repo.findAll();
    }

    public void add(String brand, String model, int year, double pricePerDay) {
        repo.add(new Car(brand, model, year, pricePerDay, "AVAILABLE"));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public void setRented(int carId, boolean rented) {
        repo.updateStatus(carId, rented ? "RENTED" : "AVAILABLE");
    }
}
