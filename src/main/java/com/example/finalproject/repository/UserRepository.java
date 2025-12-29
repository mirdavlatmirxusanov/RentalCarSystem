package com.example.finalproject.repository;

import com.example.finalproject.db.Database;
import com.example.finalproject.util.PasswordUtil;

import java.sql.*;

public class UserRepository {

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, PasswordUtil.hash(password));

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register(String username, String password) {
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, PasswordUtil.hash(password));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
