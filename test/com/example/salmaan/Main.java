package com.example.salmaan;

import com.example.salmaan.entity.main.CustomerBuilder;
import com.example.salmaan.entity.main.Customers;
import org.junit.jupiter.api.Test;

public class Main {


    @Test
    void insetCustomer() {
        Customers customer = new CustomerBuilder()
                .setFirstName("Mohamed")
                .setGander("Male")
                .setAddress("Tuurta")
                .setImage("Balah balah")
//                .setPhone("4303924")
                .setMiddleName("Saeed")
                .setLastName("Ogleh")
                .setWhoAdded("Ogleh")
                .setPayments(null)
                .build();




        System.out.println(customer);
    }
}
