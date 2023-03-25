package com.example.salmaan.dao;

import com.example.salmaan.dao.services.BoxService;
import com.example.salmaan.entity.service.Box;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class BoxServiceTest {

    @Test
    void insertBox() throws SQLException {
        System.out.println("Before");
        System.out.println(BoxService.fetchBoxes());

        Box box = new Box("Kahanada 8aad");
        BoxService.insertBox(box);

        System.out.println("After");
        System.out.println(BoxService.fetchBoxes());
    }

    @Test
    void fetchBoxes() throws SQLException {

        System.out.println(BoxService.fetchBoxes());

        System.out.println(BoxService.fetchBoxes().hashCode());

        System.out.println(BoxService.fetchBoxes().hashCode());

    }


    @Test
    void findBoxIndex() throws SQLException {
        Box box = BoxService.fetchBoxes().get(1);

        System.out.println(BoxService.findBoxIndex(BoxService.fetchBoxes(), box.getBoxId()));
    }

    @Test
    void updateBox() throws SQLException {
        Box box = BoxService.fetchBoxes().get(3);


        System.out.println("Before " + BoxService.fetchBoxes());

        box.setBoxName("Libaax box");
        box.setReady(!box.isReady());
        BoxService.updateBox(box);

        System.out.println();
        System.out.println("After " + BoxService.fetchBoxes());


    }
}