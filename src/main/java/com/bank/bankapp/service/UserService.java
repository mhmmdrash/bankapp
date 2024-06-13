package com.bank.bankapp.service;

import com.bank.bankapp.dtos.AddTxnDto;
import com.bank.bankapp.dtos.AddUserDto;
import com.bank.bankapp.models.Transaction;
import com.bank.bankapp.models.User;
import com.bank.bankapp.models.UserBankAccount;
import com.bank.bankapp.repositories.TransactionRepository;
import com.bank.bankapp.repositories.UserBankRepository;
import com.bank.bankapp.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserBankRepository bankRepository;
    private TransactionRepository transactionRepository;
    UserService(UserRepository userRepository, UserBankRepository bankRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean checkDuplicate(String email, Long accountNumber, Long phone) {
        User user = userRepository.findByEmailOrPhone(email, phone);
        if (user != null) return false;
        UserBankAccount account = bankRepository.findByAccountNumber(accountNumber);
        if (account != null) return false;

        return true;
    }

    public void addUserAndAccount(User user, UserBankAccount account) {
        User usr = userRepository.save(user);
        if (usr != null) bankRepository.save(account);
    }

    public Transaction addTransaction(AddTxnDto txnDto, long userId) throws Exception {
        Optional<User> response =  userRepository.findById(userId);
        if (!response.isPresent()) {
            throw new Exception("User not found");
        }
        User user = response.get();

        Transaction txn = new Transaction();
        txn.setCommission(txnDto.getCommission());
        txn.setGst(txnDto.getGst());
        txn.setService(txnDto.getService());
        txn.setCustomerName(txnDto.getCustomerName());
        txn.setAmount(txnDto.getAmount());
        txn.setUser(user);

        Transaction t = transactionRepository.save(txn);
        if (t == null) throw new Exception("Trnsaction failed");
        return t;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsers(long[] ids) {
        return userRepository.findByIdIn(ids);
    }

    public List<Transaction> getTransactions(double initalRange, double finalRange, long userId) {
        List<Transaction> txns = transactionRepository.getTransactionsForUserInRange(userId, initalRange, finalRange);
        return txns;
    }

    public User getUser(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) throw new Exception("User not found");
        return user.get();
    }

    public UserBankAccount getBankAccount(long userId) throws Exception {
        UserBankAccount account = bankRepository.findByUserId(userId);
        if (account == null) throw new Exception("Account does not exist");
        return account;
    }

    public List<Transaction> getAllTransactionsSorted() {
        return transactionRepository.getAllTransactions();
    }
}
