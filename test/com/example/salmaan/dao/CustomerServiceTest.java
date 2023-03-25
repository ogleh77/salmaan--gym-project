package com.example.salmaan.dao;

import com.example.salmaan.dao.main.CustomerService;
import com.example.salmaan.entity.main.CustomerBuilder;
import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.entity.service.Users;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class CustomerServiceTest {
    Users user = new Users("Mohamed", "Saeed", "4303922", "Female",
            "afternoon", "ogleh", "1122", null, "super_admin");

    @Test
    void insertOrUpdateCustomer() throws SQLException, InterruptedException {

        Customers customer = new CustomerBuilder()
                .setImage(null)
                .setAddress("Actober")
                .setPhone("4303924")
                .setGander("Male")
                .setMiddleName("Ali")
                .setFirstName("Jama")
                .setLastName("Muuse")
                .setShift("Afternoon")
                .setWeight(65)
                .setWhoAdded("Ogleh")
                .build();

        CustomerService.insertOrUpdateCustomer(customer, true, user);
    }

    @Test
    void fetchAllCustomer() throws SQLException {

        System.out.println(CustomerService.fetchAllCustomer(user));
        System.out.println(CustomerService.fetchAllCustomer(user).hashCode());

        System.out.println(CustomerService.fetchAllCustomer(user).hashCode());


    }

    @Test
    void fetchOfflineCustomer() throws SQLException {

        System.out.println(CustomerService.fetchOfflineCustomer(user));
        System.out.println(CustomerService.fetchOfflineCustomer(user).hashCode());
        System.out.println(CustomerService.fetchOfflineCustomer(user).hashCode());
    }

    @Test
    void fetchOnlineCustomer() throws SQLException {

        System.out.println(CustomerService.fetchOnlineCustomer(user));
    }
}