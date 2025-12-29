CREATE TABLE IF NOT EXISTS users (
                                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                                     username TEXT NOT NULL UNIQUE,
                                     password_hash TEXT NOT NULL,
                                     role TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS cars (
                                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                                    brand TEXT NOT NULL,
                                    model TEXT NOT NULL,
                                    year INTEGER NOT NULL,
                                    price_per_day REAL NOT NULL,
                                    status TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS rentals (
                                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                                       user_id INTEGER NOT NULL,
                                       car_id INTEGER NOT NULL,
                                       start_date TEXT NOT NULL,
                                       end_date TEXT NOT NULL,
                                       total_price REAL NOT NULL,
                                       status TEXT NOT NULL,
                                       FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(car_id) REFERENCES cars(id)
    );
