package com.example.finalproject;

import com.example.finalproject.db.DbInit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DbInit.init();

        Scene scene = new Scene(
                FXMLLoader.load(MainApp.class.getResource("/fxml/login.fxml")),
                400, 300
        );

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
