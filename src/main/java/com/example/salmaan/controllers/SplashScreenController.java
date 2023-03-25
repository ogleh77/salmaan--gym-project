package com.example.salmaan.controllers;

import animatefx.animation.FadeOut;
import com.example.salmaan.dao.main.CustomerService;
import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.entity.main.Payments;
import com.example.salmaan.entity.service.Users;
import com.example.salmaan.helpers.CommonClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SplashScreenController extends CommonClass implements Initializable {
    @FXML
    private ImageView loadingImageView;
    @FXML
    private ProgressBar progress;
    @FXML
    private Label waiting;
    @FXML
    private Label welcomeUserName;
    @FXML
    private AnchorPane splashPane;
    private final ObservableList<Customers> outdatedCustomers;
    private Stage stage;

    public SplashScreenController() {
        this.outdatedCustomers = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage) welcomeUserName.getScene().getWindow();
        });
        checkOnlineCustomers.setOnSucceeded(e -> {
            try {
                closeSplash();
                openDashboard();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
    }


    @Override
    public void setActiveUser(Users activeUser) {
        super.setActiveUser(activeUser);
        welcomeUserName.setText("Welcome " + activeUser.getUsername());

        progress.progressProperty().bind(checkOnlineCustomers.progressProperty());

        Thread thread = new Thread(checkOnlineCustomers);
        thread.setDaemon(true);
        thread.start();


    }


    public Task<Void> checkOnlineCustomers = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            int i = 0;
//            //----Check-----
            for (Customers customer : CustomerService.fetchOnlineCustomer(activeUser)) {
                i++;
                updateMessage("Loading.. " + i + "%");
                updateProgress(i, CustomerService.fetchOnlineCustomer(activeUser).size());
                for (Payments payment : customer.getPayments()) {
                    if (payment.getExpDate().isBefore(LocalDate.now())) {
                        outdatedCustomers.add(customer);
                    }

                }
            }
            Thread.sleep(100);
            return null;
        }
    };


    private void openDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/salmaan/style/views/dashboard.fxml"));
        Scene scene = new Scene(loader.load());
        DashboardController controller = loader.getController();
        controller.setActiveUser(activeUser);
        controller.setOutdatedCustomers(outdatedCustomers);
        Stage dashboardStage = new Stage();
        dashboardStage.setScene(scene);
        dashboardStage.initStyle(StageStyle.UNDECORATED);
        dashboardStage.show();
    }

    private void closeSplash() {
        FadeOut fadeOut = new FadeOut();
        fadeOut.setNode(splashPane);
        fadeOut.play();

        fadeOut.setOnFinished(e -> {
            stage.close();
        });
    }
}
