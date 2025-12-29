package com.example.finalproject.controller;

import com.example.finalproject.model.Car;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.Rental;
import com.example.finalproject.service.CarService;
import com.example.finalproject.service.CustomerService;
import com.example.finalproject.service.RentalService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class MainController {

    private final CarService carService = new CarService();
    private final CustomerService customerService = new CustomerService();
    private final RentalService rentalService = new RentalService();

    // Cars
    @FXML private TableView<Car> carTable;
    @FXML private TableColumn<Car, Number> carIdCol;
    @FXML private TableColumn<Car, String> carBrandCol;
    @FXML private TableColumn<Car, String> carModelCol;
    @FXML private TableColumn<Car, Number> carYearCol;
    @FXML private TableColumn<Car, Number> carPriceCol;
    @FXML private TableColumn<Car, String> carStatusCol;

    @FXML private TextField carBrandField;
    @FXML private TextField carModelField;
    @FXML private TextField carYearField;
    @FXML private TextField carPriceField;

    // Customers
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Number> custIdCol;
    @FXML private TableColumn<Customer, String> custNameCol;
    @FXML private TableColumn<Customer, String> custPhoneCol;

    @FXML private TextField custNameField;
    @FXML private TextField custPhoneField;

    // Rentals
    @FXML private TableView<Rental> rentalTable;
    @FXML private TableColumn<Rental, Number> rentIdCol;
    @FXML private TableColumn<Rental, Number> rentCarIdCol;
    @FXML private TableColumn<Rental, Number> rentCustIdCol;
    @FXML private TableColumn<Rental, String> rentStartCol;
    @FXML private TableColumn<Rental, String> rentEndCol;
    @FXML private TableColumn<Rental, Number> rentTotalCol;

    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;

    @FXML
    public void initialize() {
        // Cars columns
        carIdCol.setCellValueFactory(v -> new javafx.beans.property.SimpleIntegerProperty(v.getValue().getId()));
        carBrandCol.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue().getBrand()));
        carModelCol.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue().getModel()));
        carYearCol.setCellValueFactory(v -> new javafx.beans.property.SimpleIntegerProperty(v.getValue().getYear()));
        carPriceCol.setCellValueFactory(v -> new javafx.beans.property.SimpleDoubleProperty(v.getValue().getPricePerDay()));
        carStatusCol.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue().getStatus()));

        // Customers columns
        custIdCol.setCellValueFactory(v -> new javafx.beans.property.SimpleIntegerProperty(v.getValue().getId()));
        custNameCol.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue().getFullName()));
        custPhoneCol.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue().getPhone()));

        // Rentals columns
        rentIdCol.setCellValueFactory(v -> new javafx.beans.property.SimpleIntegerProperty(v.getValue().getId()));
        rentCarIdCol.setCellValueFactory(v -> new javafx.beans.property.SimpleIntegerProperty(v.getValue().getCarId()));
        rentCustIdCol.setCellValueFactory(v -> new javafx.beans.property.SimpleIntegerProperty(v.getValue().getCustomerId()));
        rentStartCol.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue().getStartDate()));
        rentEndCol.setCellValueFactory(v -> new javafx.beans.property.SimpleStringProperty(v.getValue().getEndDate()));
        rentTotalCol.setCellValueFactory(v -> new javafx.beans.property.SimpleDoubleProperty(v.getValue().getTotal()));

        refreshAll();
    }

    private void refreshAll() {
        carTable.setItems(FXCollections.observableArrayList(carService.getAll()));
        customerTable.setItems(FXCollections.observableArrayList(customerService.getAll()));
        rentalTable.setItems(FXCollections.observableArrayList(rentalService.getAll()));
    }

    // ===== Cars actions =====
    @FXML
    private void addCar() {
        try {
            String brand = carBrandField.getText().trim();
            String model = carModelField.getText().trim();
            int year = Integer.parseInt(carYearField.getText().trim());
            double price = Double.parseDouble(carPriceField.getText().trim());

            if (brand.isEmpty() || model.isEmpty()) {
                alert("Fill brand and model!");
                return;
            }

            carService.add(brand, model, year, price);
            carBrandField.clear(); carModelField.clear(); carYearField.clear(); carPriceField.clear();
            refreshAll();

        } catch (Exception e) {
            alert("Add car error: " + e.getMessage());
        }
    }

    @FXML
    private void deleteCar() {
        Car selected = carTable.getSelectionModel().getSelectedItem();
        if (selected == null) { alert("Select a car"); return; }
        carService.delete(selected.getId());
        refreshAll();
    }

    // ===== Customers actions =====
    @FXML
    private void addCustomer() {
        try {
            String name = custNameField.getText().trim();
            String phone = custPhoneField.getText().trim();
            if (name.isEmpty() || phone.isEmpty()) { alert("Fill name and phone"); return; }

            customerService.add(name, phone);
            custNameField.clear(); custPhoneField.clear();
            refreshAll();

        } catch (Exception e) {
            alert("Add customer error: " + e.getMessage());
        }
    }

    @FXML
    private void deleteCustomer() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected == null) { alert("Select a customer"); return; }
        customerService.delete(selected.getId());
        refreshAll();
    }

    // ===== Rentals actions =====
    @FXML
    private void createRental() {
        try {
            Car car = carTable.getSelectionModel().getSelectedItem();
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            if (car == null) { alert("Select a car"); return; }
            if (customer == null) { alert("Select a customer"); return; }

            if (!"AVAILABLE".equalsIgnoreCase(car.getStatus())) {
                alert("Car is not AVAILABLE");
                return;
            }

            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();
            if (start == null || end == null) { alert("Pick start/end dates"); return; }

            rentalService.rent(car.getId(), customer.getId(), start, end, car.getPricePerDay());
            startDatePicker.setValue(null);
            endDatePicker.setValue(null);
            refreshAll();

        } catch (Exception e) {
            alert("Create rental error: " + e.getMessage());
        }
    }

    @FXML
    private void deleteRental() {
        Rental selected = rentalTable.getSelectionModel().getSelectedItem();
        if (selected == null) { alert("Select a rental"); return; }
        rentalService.deleteRental(selected.getId());
        refreshAll();
    }

    private void alert(String text) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Info");
        a.setHeaderText(null);
        a.setContentText(text);
        a.showAndWait();
    }
}
