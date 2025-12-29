package com.example.finalproject.service;

import com.example.finalproject.model.Customer;
import com.example.finalproject.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository repo = new CustomerRepository();

    public List<Customer> getAll() {
        return repo.findAll();
    }

    public void add(String fullName, String phone) {
        repo.add(new Customer(fullName, phone));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
