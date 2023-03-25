package com.example.salmaan.helpers;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.entity.service.Gym;
import com.example.salmaan.entity.service.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class CommonClass {
    protected File selectedFile;

    protected boolean start = false;
    protected boolean error = false;
    private ImageView loadingImageView;
    protected final ToggleGroup genderGroup;
    private ObservableList<Control> mandatoryFields;
    private Shake shake;
    private SlideInRight slideInRight;
    private SlideInLeft slideInLeft;
    private FadeIn fadeIn;

    protected Customers customer;
    protected Users activeUser;
    protected Gym currentGym;
    protected BorderPane borderPane;
    public final String[] images = {
            "/com/example/salmaan/style/style/icons/icons/loading_5.gif",
            "/com/example/randomimage/random-images/man.gif"
    };


    public CommonClass() {
        this.genderGroup = new ToggleGroup();

    }


    protected boolean isValid(ObservableList<Control> mandatoryFields, ToggleGroup group) {

        boolean isValid = true;
        try {
            for (Control control : mandatoryFields) {
                if (control instanceof TextInputControl) {
                    if ((((TextInputControl) control).getText().isBlank() || ((TextInputControl) control).getText().isEmpty())) {
                        getShake().setNode(control);
                        control.setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                        getShake().play();
                        isValid = false;
                    } else {
                        control.setStyle(null);
                    }
                } else if (control instanceof ComboBoxBase<?> && (((ComboBoxBase<?>) control).getValue() == null)) {
                    getShake().setNode(control);
                    getShake().play();
                    control.setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                    isValid = false;
                } else {
                    control.setStyle(null);
                }
            }
            if (group.getSelectedToggle() == null) {
                getShake().setNode((Node) group.getToggles().get(0));
                getShake().play();
                getShake().setNode((Node) group.getToggles().get(1));
                ((Node) group.getToggles().get(0)).setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                ((Node) group.getToggles().get(1)).setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                getShake().play();
                isValid = false;
            } else {
                ((Node) group.getToggles().get(0)).setStyle(null);
                ((Node) group.getToggles().get(1)).setStyle(null);
            }
        } catch (NullPointerException e) {
            //  System.out.println(e.getMessage());
        }
        return isValid;
    }

    //Alert when error occur
    public Alert errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle("Error occur");
        alert.show();
        return alert;
    }

    //Alert and give message
    public Alert informationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setTitle("congratulations");
        alert.show();
        return alert;
    }

    public File selectedFile() {
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);
        return selectedFile;
    }


    //Create and load the loading gif image
    public ImageView getLoadingImageView() {
        if (loadingImageView == null) {
            URL url = getClass().getResource(images[0]);
            Image image = new Image(String.valueOf(url));
            loadingImageView = new ImageView();
            loadingImageView.setFitHeight(30);
            loadingImageView.setFitWidth(30);
            loadingImageView.setImage(image);
        }
        return loadingImageView;
    }

    public ObservableList<String> getShift() {
        ObservableList<String> shift = FXCollections.observableArrayList();
        shift.addAll("Morning", "Noon", "Afternoon", "Night");
        return shift;
    }

    public ObservableList<String> getPaidBy() {
        ObservableList<String> paidBy = FXCollections.observableArrayList();
        paidBy.addAll("eDahab", "Zaad service", "other");
        return paidBy;
    }

    protected FXMLLoader openWindow(String url, BorderPane borderPane, VBox sidePane, HBox menu, HBox profile) throws IOException {

        if (menu != null) {
            menu.setVisible(true);
            getFadeIn().setNode(menu);
            getSlideInLeft().playOnFinished(fadeIn);
            getFadeIn().play();
        }
        //top profile
        if (profile != null) {
            profile.setVisible(true);
            getFadeIn().setNode(profile);
            getFadeIn().play();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        AnchorPane anchorPane = loader.load();
        getSlideInRight().setNode(anchorPane);
        getSlideInRight().play();

        borderPane.setCenter(anchorPane);
        return loader;
    }


    private SlideInLeft getSlideInLeft() {
        if (slideInLeft == null) {
            slideInLeft = new SlideInLeft();
        }
        return slideInLeft;
    }


    private SlideInRight getSlideInRight() {
        if (slideInRight == null) {
            slideInRight = new SlideInRight();
        }
        return slideInRight;
    }

    private FadeIn getFadeIn() {
        if (fadeIn == null) {
            fadeIn = new FadeIn();
        }
        return fadeIn;
    }

    private Shake getShake() {
        if (shake == null) {
            shake = new Shake();
            System.out.println("Shake init");
        }
        return shake;
    }


    public ObservableList<Control> getMandatoryFields() {
        if (mandatoryFields == null) {
            mandatoryFields = FXCollections.observableArrayList();
        }
        return mandatoryFields;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }


    public void setActiveUser(Users activeUser) {
        this.activeUser = activeUser;
    }


    public void setCurrentGym(Gym currentGym) {
        this.currentGym = currentGym;
    }


    public void setTitle(Label title) {
        title.setText(currentGym.getGymName().toUpperCase() + "| eDahab: " +
                currentGym.geteDahab() + "| Zaad: " + currentGym.getZaad());
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
