package com.IntBuddy.IntBuddy.Configuration;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.IntBuddy.IntBuddy.Entity.UserEntity;
import com.IntBuddy.IntBuddy.Enum.Gender;
import com.IntBuddy.IntBuddy.Enum.Role;
import com.IntBuddy.IntBuddy.Repository.UserRepository;

@Component
public class AdminAccess implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AdminAccess(UserRepository userRepository,
                       BCryptPasswordEncoder encoder) {

        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {

        createAdmin(
                "bhiseamarwagholi@gmail.com",
                "Amar Bhise"
        );

       
    }

    private void createAdmin(String email, String name) {

        Optional<UserEntity> existing =
                userRepository.findByEmail(email);

        if(existing.isPresent()) {
            return;
        }

        UserEntity admin = new UserEntity();

        admin.setFullName(name);
        admin.setEmail(email);

        admin.setPassword(
                encoder.encode("admin123")
        );

        admin.setRole(Role.ADMIN);
        admin.setCountry("India");
        admin.setState("Maharashtra");
        admin.setPhoneno("9730695483");
        admin.setOtp("000000");

        userRepository.save(admin);

        admin.setGender(Gender.MALE);

        userRepository.save(admin);
        System.out.println("Admin Created : " + email);
    }
}