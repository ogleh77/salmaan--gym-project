module com.example.salmaan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires AnimateFX;
    requires org.xerial.sqlitejdbc;
    requires junit;

    opens com.example.salmaan to javafx.fxml;
    exports com.example.salmaan;
    opens com.example.salmaan.controllers.main to javafx.fxml;
    opens com.example.salmaan.controllers to javafx.fxml;
    opens com.example.salmaan.controllers.service to javafx.fxml;
    opens com.example.salmaan.controllers.info to javafx.fxml;
    exports com.example.salmaan.entity.main;
}