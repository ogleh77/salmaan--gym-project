package com.example.salmaan.models;

import com.example.salmaan.entity.service.Box;
import com.example.salmaan.helpers.DbConnection;
import com.example.salmaan.helpers.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoxModel implements Repo<Box> {
    private static final Connection connection = DbConnection.getConnection();


    @Override
    public void insert(Box box) throws SQLException {
        String insertBox = " INSERT INTO box(box_name) " +
                "VALUES ('" + box.getBoxName() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(insertBox);
        System.out.println("Box saved....");
        statement.close();
    }

    @Override
    public void update(Box box) throws SQLException {
        String boxQuery = "UPDATE box SET is_ready=false ,box_name='" + box.getBoxName() + "' WHERE box_id=" + box.getBoxId();

        if (!box.isReady()) {
            boxQuery = "UPDATE box SET is_ready=true, box_name='" + box.getBoxName() + "' WHERE box_id=" + box.getBoxId();
        }
        Statement statement = connection.createStatement();
        statement.executeUpdate(boxQuery);

      //  box.setReady(box.isReady());
        System.out.println(box.isReady() ? "box made false " : "box made true");

    }


    public ObservableList<Box> fetchBoxes() throws SQLException {
        ObservableList<Box> boxes = FXCollections.observableArrayList();
        Box box;

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM box");

        while (rs.next()) {
            box = new Box(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            boxes.add(box);
        }
        rs.close();
        statement.close();
        return boxes;
    }


//    public void setTookBoxIsReadyFalse(Box box) throws SQLException {
//        String boxFalseQuery = "UPDATE box SET is_ready=false WHERE box_id=" + box.getBoxId();
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(boxFalseQuery);
//        box.setReady(false);
//        System.out.println(box.getBoxId() + " made false");
//    }
//
//    public void setTookBoxIsReadyTrue(Box box) throws SQLException {
//        String boxFalseQuery = "UPDATE box SET is_ready=true WHERE box_id=" + box.getBoxId();
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(boxFalseQuery);
//        //set the box off
//        box.setReady(true);
//        System.out.println(box.getBoxName() + " made false");
//    }


}
