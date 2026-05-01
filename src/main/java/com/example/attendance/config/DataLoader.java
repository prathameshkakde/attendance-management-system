package com.example.attendance.config;

import com.example.attendance.model.User;
import com.example.attendance.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadUsers(UserService userService) {

        return args -> {

            // Create admin user
            if (userService.findByUsername("admin").isEmpty()) {

                User admin = new User(
                        "admin",
                        "admin123",
                        "ADMIN"
                );

                userService.saveUser(admin);
            }

            // Create student user
            if (userService.findByUsername("student").isEmpty()){

                User student = new User(
                        "student",
                        "student123",
                        "STUDENT"
                );

                userService.saveUser(student);
            }
        };
    }
}
