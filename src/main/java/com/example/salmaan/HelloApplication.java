package com.example.salmaan;

import com.example.salmaan.controllers.SearchController;
import com.example.salmaan.dao.services.UsersService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/salmaan/style/views/service/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //SearchController controller = fxmlLoader.getController();
     //   controller.setActiveUser(UsersService.users().get(0));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}