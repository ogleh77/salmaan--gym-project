package com.example.salmaan.dao.main;

import com.example.salmaan.entity.main.Customers;
import com.example.salmaan.entity.main.Payments;
import com.example.salmaan.entity.service.Pending;
import com.example.salmaan.helpers.CustomException;
import com.example.salmaan.models.main.PaymentModel;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class PaymentService {
    private static final PaymentModel paymentModel = new PaymentModel();

    public static void insertPayment(Customers customer) throws SQLException {
        try {
            String customerGander = customer.getGander();
            paymentModel.insertPayment(customer.getPhone(), customerGander, customer.getPayments().get(0));
        } catch (SQLException e) {
            throw new CustomException("Khalad ayaaa ka dhacay " + e.getMessage() + " " +
                    "\n fadlan dib u search garee customerka kadibna payment usamee");
        }
    }

    public static ObservableList<Payments> fetchCustomersOfflinePayment(String customerPhone) throws SQLException {
        return paymentModel.fetchCustomersOfflinePayment(customerPhone);
    }

    public static ObservableList<Payments> fetchCustomersOnlinePayment(String customerPhone) throws SQLException {
        return paymentModel.fetchCustomersOnlinePayment(customerPhone);
    }

    public static ObservableList<Payments> fetchAllCustomersPayments(String customerPhone) throws SQLException {
        return paymentModel.fetchAllCustomersPayments(customerPhone);
    }

    //----------------------Payment pending-------------------

    public static void pendPayment(Payments payment, int allowedDays) throws SQLException {
        LocalDate exp = payment.getExpDate();
        LocalDate pendingDate = LocalDate.now();
        int daysRemind = Period.between(pendingDate, exp).getDays();
        if (daysRemind <= allowedDays) {
            throw new CustomException("Fadlan lama xidhi karo event kan waayo wuxu ka hoseya wakhtiga lo asteyey");
        }


        paymentModel.pendPayment(payment.getBox(), payment.getPaymentID(), daysRemind);
    }

    public static void updatePendingPayment(Pending pending) throws SQLException {
        int daysRemain = pending.getDaysRemain();

        paymentModel.updatePendingPayment(LocalDate.now().plusDays(daysRemain), pending);

    }

    public static ObservableList<Pending> fetchPendedPayment() throws SQLException {
        return paymentModel.fetchPendedPayment();
    }

}