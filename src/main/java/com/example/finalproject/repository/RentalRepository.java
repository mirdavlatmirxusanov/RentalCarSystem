package com.example.finalproject.repository;

import com.example.finalproject.db.Database;
import com.example.finalproject.model.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository {

    public List<Rental> findAll() {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT id, car_id, customer_id, start_date, end_date, total FROM rentals ORDER BY id DESC";

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Rental(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getInt("customer_id"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getDouble("total")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("findAll rentals error: " + e.getMessage(), e);
        }
    }

    public void add(Rental rental) {
        String sql = "INSERT INTO rentals(car_id, customer_id, start_date, end_date, total) VALUES(?,?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, rental.getCarId());
            ps.setInt(2, rental.getCustomerId());
            ps.setString(3, rental.getStartDate());
            ps.setString(4, rental.getEndDate());
            ps.setDouble(5, rental.getTotal());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("add rental error: " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM rentals WHERE id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("delete rental error: " + e.getMessage(), e);
        }
    }
}
