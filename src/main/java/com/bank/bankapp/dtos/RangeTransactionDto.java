package com.bank.bankapp.dtos;

import com.bank.bankapp.models.Transaction;
import com.bank.bankapp.models.User;
import com.bank.bankapp.models.UserBankAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RangeTransactionDto {
    private AddUserDto userAndBankDetails;
    private List<AddTxnDto> transactionDetails;
    
    public RangeTransactionDto(User user, UserBankAccount account, List<Transaction> txns) {
        this.userAndBankDetails.setBankIfsc(account.getBankIfsc());
        this.userAndBankDetails.setBankName(account.getBankName());
        this.userAndBankDetails.setAccountNumber(account.getAccountNumber());
        this.userAndBankDetails.setFirstName(userAndBankDetails.getFirstName());
        this.userAndBankDetails.setLastName(user.getLastName());
        this.userAndBankDetails.setEmail(user.getEmail());
        this.userAndBankDetails.setPhone(user.getPhone());
        
        for (Transaction tx: txns) {
            AddTxnDto txn = new AddTxnDto();
            txn.setCommission(tx.getCommission());
            txn.setGst(tx.getGst());
            txn.setAmount(tx.getAmount());
            txn.setService(tx.getService());
            txn.setCustomerName(tx.getCustomerName());

            this.transactionDetails.add(txn);
        }
    }

    @Override
    public String toString() {
        return "RangeTransactionDto{" +
                "userAndBankDetails=" + userAndBankDetails +
                ", transactionDetails=" + transactionDetails +
                '}';
    }
}
