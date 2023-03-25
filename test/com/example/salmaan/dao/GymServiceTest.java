package com.example.salmaan.dao;

import com.example.salmaan.dao.services.GymService;
import com.example.salmaan.entity.service.Gym;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class GymServiceTest {

    @Test
    void updateGym() throws SQLException {
        Gym gym = GymService.getGym();
        System.out.println("Before\n" + gym);
        gym.setGymName("Ogleh fitness center");
        gym.setPendingDate(16);
        GymService.updateGym(gym);
        System.out.println("after\n" + gym);

    }

    @Test
    void getGym() throws SQLException {
        System.out.println(GymService.getGym());
    }
}