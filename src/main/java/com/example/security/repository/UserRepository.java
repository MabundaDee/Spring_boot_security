package com.example.security.repository;

import com.example.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // find user by email
    Optional<User> findByEmail(String email);
}
