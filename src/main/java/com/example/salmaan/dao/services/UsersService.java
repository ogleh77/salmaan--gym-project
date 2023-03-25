package com.example.salmaan.dao.services;

import com.example.salmaan.entity.service.Users;
import com.example.salmaan.helpers.CustomException;
import com.example.salmaan.models.services.UserModel;
import javafx.collections.ObservableList;


import java.sql.SQLException;

public class UsersService {

    private static UserModel userModel;
    private static ObservableList<Users> users = null;

    static {
        System.out.println("User service called...");
        if (userModel == null) {
            userModel = new UserModel();
        }
    }

    public static void insertUser(Users user) throws CustomException {
        try {

            userModel.insert(user);
        } catch (SQLException e) {
            if (e.getMessage().contains("(UNIQUE constraint failed: users.username)")) {
                throw new CustomException("username-kan hore ayaa loo isticmalay fadlan dooro mid kale");
            } else {
                throw new CustomException("Khalad baa ka dhacay " + e.getMessage());
            }
        }
    }

    //    public static void updateUser(Users user) throws CustomException {
//        try {
//            userModel.updateUser(user);
//        } catch (SQLException e) {
//            if (e.getMessage().contains("(UNIQUE constraint failed: users.username)")) {
//                throw new CustomException("username-kan hore ayaa loo isticmalay fadlan dooro mid kale");
//            } else {
//                throw new CustomException("Khalad baa ka dhacay " + e.getMessage());
//            }
//        }
//    }
    public static ObservableList<Users> users() throws SQLException {
        try {
            if (users == null) {
                users = userModel.fetch();
                users.add(new Users(0, "Luul", "Muuse", "4476619", "Male",
                        "Morning", "lulka **", "4000", null, "super admin"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return users;
    }

}
