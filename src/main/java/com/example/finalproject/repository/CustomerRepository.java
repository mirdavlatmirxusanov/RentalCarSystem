package com.example.finalproject.repository;

import com.example.finalproject.db.Database;
import com.example.finalproject.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT id, full_name, phone FROM customers ORDER BY id DESC";

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("phone")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("findAll customers error: " + e.getMessage(), e);
        }
    }

    public void add(Customer customer) {
        String sql = "INSERT INTO customers(full_name, phone) VALUES(?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getPhone());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("add customer error: " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM customers WHERE id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("delete customer error: " + e.getMessage(), e);
        }
    }
}
