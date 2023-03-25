package com.example.salmaan.controllers;

import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.helpers.CommonClass;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificationController extends CommonClass implements Initializable {
    @FXML
    private VBox vbox;
    private ObservableList<Customers> outdatedCustomers;
    private VBox sidePane;
    private HBox menuBox;
    private HBox HBoxProfile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            FXMLLoader loader;
            AnchorPane anchorPane;
            for (Customers customer : outdatedCustomers) {
                loader = new FXMLLoader(getClass().getResource("/com/example/salmaan/style/views/customer-card.fxml"));
                try {
                    anchorPane = loader.load();
                    CardController controller = loader.getController();
                    controller.setCustomer(customer);
                    controller.setBorderPane(borderPane);
                    controller.setSidePane(sidePane);
                    controller.setHBoxProfile(HBoxProfile);
                    controller.setMenuBox(menuBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                vbox.getChildren().add(anchorPane);
            }
        });
    }

    public void setOutdatedCustomers(ObservableList<Customers> outdatedCustomers) {
        this.outdatedCustomers = outdatedCustomers;
    }

    @Override
    public void setBorderPane(BorderPane borderPane) {
        super.setBorderPane(borderPane);
    }

    public void setSidePane(VBox sidePane) {
        this.sidePane = sidePane;
    }

    public void setHBoxProfile(HBox HBoxProfile) {
        this.HBoxProfile = HBoxProfile;
    }

    public void setMenuBox(HBox menuBox) {
        this.menuBox = menuBox;
    }
}
