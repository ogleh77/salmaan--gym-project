package com.example.salmaan.dao;

import com.example.salmaan.dao.services.UsersService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UsersServiceTest {

    @Test
    void users() throws SQLException {

        System.out.println(UsersService.users());
        System.out.println(UsersService.users().hashCode());

        System.out.println(UsersService.users().hashCode());

        System.out.println(UsersService.users().hashCode());

    }
}