package com.example.salmaan.dao;

import com.example.salmaan.dao.main.PaymentService;
import com.example.salmaan.dao.services.BoxService;
import com.example.salmaan.entity.main.CustomerBuilder;
import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.entity.main.PaymentBuilder;
import com.example.salmaan.entity.main.Payments;
import com.example.salmaan.entity.service.Box;
import com.example.salmaan.entity.service.Pending;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

class PaymentServiceTest {

    @Test
    void fetchCustomersOfflinePayment() throws SQLException {
//        System.out.println(PaymentService.fetchCustomersOfflinePayment("4303924"));
//
//        System.out.println(PaymentService.fetchCustomersOfflinePayment("4303924"));
        System.out.println(PaymentService.fetchCustomersOfflinePayment("4303924"));
//
//        System.out.println();
//        System.out.println();
//        System.out.println(PaymentService.fetchCustomersOfflinePayment("4303923"));
//
        //  System.out.println(PaymentService.fetchCustomersOfflinePayment("4303923"));
//

        System.out.println(PaymentService.fetchCustomersOfflinePayment("4303923"));
    }

    @Test
    void insertPayment() throws SQLException {

        Box box = BoxService.fetchBoxes().get(7);

        System.out.println(box);
        Payments payment = new PaymentBuilder()
                .setExpDate(LocalDate.now().minusDays(10))
                .setPaymentDate(LocalDate.now().toString())
                .setAmountPaid(12)
                .setOnline(false)
//                .setCustomerFK("4303924")
                .setDiscount(0)
                .setPaidBy("Golis")
                .setPoxing(false).build();

        payment.setBox(box);


        Customers customer = new CustomerBuilder()
                .setImage(null)
                .setAddress("tuurta")
                .setPhone("4303923")
                .setGander("Male")
                .setMiddleName("Saeed")
                .setFirstName("Mohamed")
                .setLastName("Ogleh")
                .setShift("Afternoon")
                .setWeight(65)
                .setWhoAdded("Ogleh")
                .build();
        customer.getPayments().add(0, payment);
        System.out.println("Before took the box \n" + BoxService.fetchBoxes().get(7));
        PaymentService.insertPayment(customer);

        System.out.println("After took the box \n" + BoxService.fetchBoxes().get(7));
        System.out.println(customer.getPayments().get(0).display());
    }

    @Test
    void fetchCustomersOnlinePayment() throws SQLException {
        System.out.println(PaymentService.fetchCustomersOfflinePayment("4303924"));
    }

    @Test
    void fetchAllCustomersPayments() throws SQLException {
        System.out.println(PaymentService.fetchAllCustomersPayments("4303924"));
    }

    @Test
    void pendPayment() throws SQLException {
        Payments payment = PaymentService.fetchCustomersOnlinePayment("4303923").get(0);
        System.out.println(payment.display());
        PaymentService.pendPayment(payment, -50);
    }

    @Test
    void updatePendingPayment() throws SQLException {
        Pending pending = PaymentService.fetchPendedPayment().get(1);

        PaymentService.updatePendingPayment(pending);
        System.out.println(pending);
    }

    @Test
    void fetchPendedPayment() throws SQLException {
        //  System.out.println();

//        Pending pending = PaymentService.fetchPendedPayment().get(1);
//
//        PaymentService.updatePendingPayment(pending);

        //    System.out.println(PaymentService.fetchPendedPayment().size());


        // TODO: 03/03/2023 Insha Allah kala habee payment model and servise sido kale ka dhig kuwo hal data soo cesha qaybta customerka
        //
    }

}