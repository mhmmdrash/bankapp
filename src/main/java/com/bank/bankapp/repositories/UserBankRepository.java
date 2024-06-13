package com.bank.bankapp.repositories;

import com.bank.bankapp.models.UserBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBankRepository extends JpaRepository<UserBankAccount, Long> {
    UserBankAccount save(UserBankAccount account);
    UserBankAccount findByAccountNumber(Long accountNumber);
    UserBankAccount findByUserId(long userId);
}
