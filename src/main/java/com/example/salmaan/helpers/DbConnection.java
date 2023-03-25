package com.example.salmaan.helpers;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnection {
    private static Connection connection;

    private DbConnection() throws SQLException {
    }


    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:src/database/database.db");
                System.out.println("Opened database successfully");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
            //System.exit(0);
        }
        return connection;
    }
}
