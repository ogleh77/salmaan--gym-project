package com.example.salmaan.controllers;

import animatefx.animation.FadeOut;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideOutLeft;
import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.entity.service.Gym;
import com.example.salmaan.entity.service.Users;
import com.example.salmaan.helpers.CommonClass;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends CommonClass implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox menuHbox;
    @FXML
    private Label notiLebal;
    @FXML
    private Circle profileImage;
    @FXML
    private Label profileUserName;
    @FXML
    private VBox sidePane;
    @FXML
    private StackPane stackPane;
    @FXML
    private HBox topProfile;

    private boolean visible = false;
    private boolean openSearch = false;
    private ObservableList<Customers> outdatedCustomers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            borderPane.setLeft(null);
            //  notiLebal.setText();
            ///Notification done insha Allah


            // TODO: 04/03/2023  Out dated customers
        });
    }


    @FXML
    void cancelHandler(MouseEvent event) {
        FadeOut fadeOut = new FadeOut(stackPane);
        Stage currentStage = (Stage) topProfile.getScene().getWindow();
        fadeOut.play();
        fadeOut.setOnFinished(e -> {
            currentStage.close();
        });

    }

    @FXML
    void expandHandler(MouseEvent event) {

    }

    @FXML
    void menuClicked(MouseEvent event) {
        if (visible) {
            SlideOutLeft slideOutLeft = new SlideOutLeft();
            slideOutLeft.setNode(sidePane);
            slideOutLeft.play();
            slideOutLeft.setOnFinished(e -> {
                borderPane.setLeft(null);
            });
        } else {
            new SlideInLeft(sidePane).play();
            borderPane.setLeft(sidePane);
        }
        visible = !visible;
    }


    @FXML
    void registrationHandler(ActionEvent event) {

    }


    @FXML
    void notificationClickHandler(MouseEvent event) throws IOException {
        openNotifications();
    }

    @FXML
    void notificationTopClickHandler(MouseEvent event) throws IOException {
        openNotifications();
    }

    @FXML
    void searchHandler(MouseEvent event) {

    }

    @Override
    public void setActiveUser(Users activeUser) {
        super.setActiveUser(activeUser);
    }

    @Override
    public void setCurrentGym(Gym currentGym) {
        super.setCurrentGym(currentGym);
    }

    public void setOutdatedCustomers(ObservableList<Customers> outdatedCustomers) {
        this.outdatedCustomers = outdatedCustomers;
    }

    private void openRegistrations() {
        //   com/example/salmaan/style/views/dashboard.fxml
    }

    private void openNotifications() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/salmaan/style/views/notifications.fxml"));
        Scene scene = new Scene(loader.load());
        NotificationController controller = loader.getController();
        controller.setOutdatedCustomers(outdatedCustomers);
        controller.setBorderPane(borderPane);
        controller.setHBoxProfile(topProfile);
        controller.setMenuBox(menuHbox);
        controller.setSidePane(sidePane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
