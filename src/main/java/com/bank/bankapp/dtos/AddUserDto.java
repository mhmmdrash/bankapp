package com.bank.bankapp.dtos;

import com.bank.bankapp.models.User;
import com.bank.bankapp.models.UserBankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {
//    user details
    private String firstName;
    private String lastName;
    private String email;
    private Long phone;

//    bank details
    private String bankName;
    private String bankIfsc;
    private long accountNumber;

    public User toUser() {
       User user = new User();
       user.setFirstName(firstName);
       user.setLastName(lastName);
       user.setEmail(email);
       user.setPhone(phone);
       return user;
    }

    public UserBankAccount toAccount() {
        UserBankAccount account = new UserBankAccount();
        account.setAccountNumber(accountNumber);
        account.setBankIfsc(bankIfsc);
        account.setBankName(bankName);
        account.setUser(toUser());
        return account;
    }

    @Override
    public String toString() {
        return "AddUserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", bankName='" + bankName + '\'' +
                ", bankIfsc='" + bankIfsc + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
