package com.bank.bankapp.controller;

import com.bank.bankapp.dtos.AddUserDto;
import com.bank.bankapp.dtos.AddTxnDto;
import com.bank.bankapp.dtos.DetailsDto;
import com.bank.bankapp.dtos.RangeTransactionDto;
import com.bank.bankapp.models.Transaction;
import com.bank.bankapp.models.User;
import com.bank.bankapp.models.UserBankAccount;
import com.bank.bankapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class bankController {

    private UserService userService;
    bankController(UserService userService) {
       this.userService = userService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<String> addUserDetails(@RequestBody AddUserDto userDto) {
        boolean check = userService.checkDuplicate(
            userDto.getEmail(),
            userDto.getAccountNumber(),
            userDto.getPhone()
        );
        if (!check) return ResponseEntity.status(400).body("Credentials already exists");

        User user = userDto.toUser();
        UserBankAccount account = userDto.toAccount();
        userService.addUserAndAccount(user, account);
        return ResponseEntity.ok("User and account credentials added");
    }

    @PostMapping("/txn/add/{userId}")
    public ResponseEntity<Transaction> addTransaction(
        @PathVariable("userId") long userId,
        @RequestBody AddTxnDto txnDto
    ) throws Exception {
        if (txnDto.getAmount() <= 0) return ResponseEntity.status(400).body(null);
        Transaction tx = userService.addTransaction(txnDto, userId);
        return ResponseEntity.ok(tx);
    }

    @PostMapping("/txn/get/details")
    public ResponseEntity<List<User>> getDetails(@RequestBody DetailsDto idsDto) {
        List<User> users;
        if (idsDto.getIds().length == 0) {
            users = userService.getAllUsers();
        } else {
            users = userService.getAllUsers(idsDto.getIds());
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/txn/amount/{initialRange}/{finalRange}")
    public ResponseEntity<String> getTransactions(
            @PathVariable("initialRange") double initialRange,
            @PathVariable("finalRange") double finalRange,
            @RequestParam("userId") long userId
    ) throws Exception {
        List<Transaction> txns = userService.getTransactions(initialRange, finalRange, userId);
        User user = userService.getUser(userId);
        UserBankAccount account = userService.getBankAccount(userId);
        RangeTransactionDto res = new RangeTransactionDto(user, account, txns);
        return ResponseEntity.ok(res.toString());
    }

    @GetMapping("/sort/amount")
    public ResponseEntity<List<String>> getAllTransactionsSorted() {
        List<Transaction> txns = userService.getAllTransactionsSorted();
        List<String> res = new ArrayList<>();
        for (Transaction tx: txns) {
            AddTxnDto txn = new AddTxnDto();
            txn.setCommission(tx.getCommission());
            txn.setGst(tx.getGst());
            txn.setAmount(tx.getAmount());
            txn.setService(tx.getService());
            txn.setCustomerName(tx.getCustomerName());

            res.add(txn.toString());
        }

        return ResponseEntity.ok(res);
    }

}
