module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.finalproject.controller to javafx.fxml;
    exports com.example.finalproject;
}
