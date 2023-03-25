package com.example.salmaan.dao.services;

import com.example.salmaan.entity.service.Gym;
import com.example.salmaan.helpers.CustomException;
import com.example.salmaan.models.services.GymModel;

import java.sql.SQLException;

public class GymService {
    private static final GymModel gymModel = new GymModel();
    private static Gym currentGym = null;

    public static void updateGym(Gym gym) throws SQLException {
        try {
            gymModel.update(gym);
            currentGym = gym;
        } catch (SQLException e) {
            throw new CustomException("Khalad ayaa dhacay fadlan hubi in wax ka bedelku dhaqan galay\n" +
                    "hadii kalese ku celi mar kale " + e.getMessage());
        }
    }

    public static Gym getGym() throws SQLException {
        if (currentGym == null) {
            System.out.println("Called");
            currentGym = gymModel.currentGym();
        }
        return currentGym;
    }
}
