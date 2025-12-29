package com.example.finalproject.controller;

import com.example.finalproject.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginController {

    @FXML private TextField username;
    @FXML private PasswordField password;

    private final UserRepository repo = new UserRepository();

    @FXML
    void login() throws Exception {
        if (repo.login(username.getText(), password.getText())) {
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/fxml/main.fxml")),
                    1000, 650
            ));
        }
    }

    @FXML
    void register() {
        repo.register(username.getText(), password.getText());
    }
}
