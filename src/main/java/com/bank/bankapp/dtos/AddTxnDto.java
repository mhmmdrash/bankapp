package com.bank.bankapp.dtos;

import com.bank.bankapp.models.Transaction;
import com.bank.bankapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTxnDto {
    private String customerName;
    private String service;
    private double amount;
    private double gst;
    private double commission;
//    private Long userId;

//    public Transaction toTrasaction() {
//        Transaction txn = new Transaction();
//        txn.setCustomerName(customerName);
//        txn.setAmount(amount);
//        txn.setService(service);
//        txn.setCommission(commission);
//        txn.setGst(gst);
//
//        User user = new User();
//        user.setId(userId);
//        txn.setUser(user);
//
//        return txn;
//    }

    @Override
    public String toString() {
        return "addTxnDto{" +
                "customerName='" + customerName + '\'' +
                ", service='" + service + '\'' +
                ", amount=" + amount +
                ", gst=" + gst +
                ", commission=" + commission +
                '}';
    }
}
