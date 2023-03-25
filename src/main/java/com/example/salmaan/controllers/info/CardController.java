package com.example.salmaan.controllers.info;

import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.helpers.CommonClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class CardController extends CommonClass implements Initializable {
    @FXML
    private ImageView customerPhoto;

    @FXML
    private Label fullName;

    @FXML
    private Label lastPaid;

    @FXML
    private Label outDated;

    @FXML
    private Label phone;

    @FXML
    private Label shift;
    private VBox sidePane;
    private HBox menuBox;
    private HBox HBoxProfile;
    private Stage notificationStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            notificationStage = (Stage) shift.getScene().getWindow();
        });
    }

    @FXML
    void updateHandler(ActionEvent event) throws IOException {
        notificationStage.close();
        openWindow("/com/example/salmaan/style/views/main/payments.fxml", borderPane, sidePane, menuBox, HBoxProfile);
    }

    @Override
    public void setCustomer(Customers customer) {
        super.setCustomer(customer);

        LocalDate exp = customer.getPayments().get(0).getExpDate();
        LocalDate today = LocalDate.now();
        Period period = Period.between(exp, today);

        fullName.setText(customer.getFirstName() + " " + customer.getMiddleName() + " " + customer.getLastName());
        lastPaid.setText(customer.getPayments().get(0).getPaymentDate());
        outDated.setText((period.getDays()) + " days ago");
        phone.setText(customer.getPhone());
        shift.setText(customer.getShift());

        try {
            if (customer.getImage() != null) {
                customerPhoto.setImage(new Image(new FileInputStream(customer.getImage())));
            }
        } catch (FileNotFoundException e) {
            errorMessage(e.getMessage());
        }

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
