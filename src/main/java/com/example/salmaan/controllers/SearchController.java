package com.example.salmaan.controllers;

import com.example.salmaan.dao.main.CustomerService;
import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.entity.service.Users;
import com.example.salmaan.helpers.CommonClass;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchController extends CommonClass implements Initializable {
    @FXML
    private TableColumn<Customers, String> fullName;

    @FXML
    private TableColumn<Customers, String> gander;

    @FXML
    private TableColumn<Customers, JFXButton> payment;

    @FXML
    private TableColumn<Customers, String> phone;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Customers> tbView;
    @FXML
    private TableColumn<Customers, JFXButton> update;

    @FXML
    private TableColumn<Customers, JFXButton> view;
    private Stage searchSage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            try {
                searchSage=(Stage)tbView.getScene().getWindow();
                initTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        if (start) {
            service.restart();

        } else {
            service.start();
            start = true;
        }

        service.setOnSucceeded(e -> {
            tbView.refresh();
            System.out.println("Done");
            try {
                customerButtons();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void cancelPane(MouseEvent mouseEvent) {

    }


    @Override
    public void setActiveUser(Users activeUser) {
        super.setActiveUser(activeUser);
    }

    private final Service<Void> service = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(2000);
                    CustomerService.fetchAllCustomer(activeUser);
                    return null;
                }
            };
        }
    };

    private void initTable() throws SQLException {
        fullName.setCellValueFactory(customers -> new SimpleStringProperty(
                customers.getValue().getFirstName() + "  " + customers.getValue().getMiddleName()
                        + "  " + customers.getValue().getLastName()));

        view.setCellValueFactory(new PropertyValueFactory<>("information"));
        update.setCellValueFactory(new PropertyValueFactory<>("update"));
        payment.setCellValueFactory(new PropertyValueFactory<>("paymentBtn"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        if (CustomerService.fetchAllCustomer(activeUser) == null) {
            Label label = new Label("Loading customers please wait...");
            tbView.setPlaceholder(label);
        }
        tbView.setItems(CustomerService.fetchAllCustomer(activeUser));

    }


    private void customerButtons() throws SQLException {
        for (Customers customer : CustomerService.fetchAllCustomer(activeUser)) {
            EventHandler<MouseEvent> updateHandler = event -> {
                System.out.println("Update pressed...." + customer.getFirstName());
            };

            EventHandler<MouseEvent> informationHandler = event -> {
                System.out.println("payment pressed...." + customer.getFirstName());
                try {
                    searchSage.close();
                    openCustomerInfo(customer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };

            // EventHandler<MouseEvent> informationHandler = event -> System.out.println("info touched " + customer.getFirstName());
            customer.getInformation().addEventFilter(MouseEvent.MOUSE_CLICKED, informationHandler);
            customer.getUpdate().addEventFilter(MouseEvent.MOUSE_CLICKED, updateHandler);
            //customer.getPaymentBtn().addEventFilter(MouseEvent.MOUSE_CLICKED, paymentHandler);

        }
    }


    private void openCustomerInfo(Customers customer) throws IOException {
        FXMLLoader loader = openWindow("/com/example/salmaan/style/views/customer-info.fxml", borderPane, null, null, null);
        CustomerInfoController controller = loader.getController();
        controller.setCustomer(customer);
    }


    @Override
    public void setBorderPane(BorderPane borderPane) {
        super.setBorderPane(borderPane);
    }
}
