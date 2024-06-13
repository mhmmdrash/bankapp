package com.bank.bankapp.repositories;

import com.bank.bankapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction save(Transaction txn);

    @Query(value = "select * from transaction t where t.userId = :userId and t.amount between :initialRange and :finalRange", nativeQuery = true)
    List<Transaction> getTransactionsForUserInRange(
            @Param("userId") long userId,
            @Param("initialRange") double initialRange,
            @Param("finalRange") double finalRange
    );

    @Query(value = "select * from transaction t order by t.amount asc", nativeQuery = true)
    List<Transaction> getAllTransactions();
}
