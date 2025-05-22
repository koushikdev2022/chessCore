package com.iksen.chessCore.seeders;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.iksen.chessCore.model.User;
import com.iksen.chessCore.repositary.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
   public UserSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            List<User> users = List.of(
                User.builder()
                        .firstName("Alice")
                        .lastName("Smith")
                        .userName("alice123")
                        .email("alice@yopmail.com")
                        .password(passwordEncoder.encode("12345678"))
                        .mobile("1234567890")
                        .parentId(0L)
                        .status(1)
                        .isDeleted(0)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                User.builder()
                        .firstName("Srk")
                        .lastName("Khan")
                        .userName("srk")
                        .email("srk@yopmail.com")
                        .password(passwordEncoder.encode("12345678"))
                        .mobile("1234567890")
                        .parentId(0L)
                        .status(1)
                        .isDeleted(0)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                User.builder()
                        .firstName("Bob")
                        .lastName("Johnson")
                        .userName("bobthebuilder")
                        .email("bob@yopmail.com")
                        .password(passwordEncoder.encode("12345678"))
                        .mobile("9876543210")
                        .parentId(0L)
                        .status(1)
                        .isDeleted(0)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
            );

            userRepository.saveAll(users);
            System.out.println("User seed data inserted.");
        } else {
            System.out.println("User table already contains data. Skipping seeding.");
        }
    }
}
