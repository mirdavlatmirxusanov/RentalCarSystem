package com.example.finalproject.service;

import com.example.finalproject.model.Rental;
import com.example.finalproject.repository.RentalRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RentalService {
    private final RentalRepository repo = new RentalRepository();
    private final CarService carService = new CarService();

    public List<Rental> getAll() {
        return repo.findAll();
    }

    public void rent(int carId, int customerId, LocalDate start, LocalDate end, double pricePerDay) {
        long days = ChronoUnit.DAYS.between(start, end) + 1;
        if (days <= 0) throw new IllegalArgumentException("End date must be after start date");
        double total = days * pricePerDay;

        repo.add(new Rental(carId, customerId, start.toString(), end.toString(), total));
        carService.setRented(carId, true);
    }

    public void deleteRental(int rentalId) {
        repo.deleteById(rentalId);
    }
}
