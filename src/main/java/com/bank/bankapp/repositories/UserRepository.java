package com.bank.bankapp.repositories;

import com.bank.bankapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findByEmailOrPhone(String email, long phone);
    List<User> findByIdIn(long[] userIds);
    List<User> findAll();
}
