module com.example.salmaan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires AnimateFX;
    requires org.xerial.sqlitejdbc;
    requires junit;

    opens com.example.salmaan to javafx.fxml;
    opens com.example.salmaan.controllers to javafx.fxml;
    exports com.example.salmaan;
    // exports com.example.salmaan.entities.main;
}