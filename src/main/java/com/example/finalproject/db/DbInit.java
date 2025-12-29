package com.example.finalproject.db;

import java.sql.Connection;
import java.sql.Statement;

public class DbInit {

    public static void init() {
        try (Connection c = Database.getConnection();
             Statement st = c.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL
                )
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
