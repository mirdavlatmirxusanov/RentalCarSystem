package com.example.finalproject.repository;

import com.example.finalproject.db.Database;
import com.example.finalproject.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT id, brand, model, year, price_per_day, status FROM cars ORDER BY id DESC";

        try (Connection c = Database.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price_per_day"),
                        rs.getString("status")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("findAll cars error: " + e.getMessage(), e);
        }
    }

    public void add(Car car) {
        String sql = "INSERT INTO cars(brand, model, year, price_per_day, status) VALUES(?,?,?,?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setDouble(4, car.getPricePerDay());
            ps.setString(5, car.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("add car error: " + e.getMessage(), e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM cars WHERE id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("delete car error: " + e.getMessage(), e);
        }
    }

    public void updateStatus(int id, String status) {
        String sql = "UPDATE cars SET status=? WHERE id=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("update status error: " + e.getMessage(), e);
        }
    }
}
