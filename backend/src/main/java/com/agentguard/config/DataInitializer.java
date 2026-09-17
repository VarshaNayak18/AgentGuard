package com.agentguard.config;

import com.agentguard.model.User;
import com.agentguard.model.UserRole;
import com.agentguard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Value("${agentguard.admin.username}")
    private String adminUsername;

    @Value("${agentguard.admin.password}")
    private String adminPassword;

    @Bean
    public CommandLineRunner initializeAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByUsername(adminUsername).isEmpty()) {

                User admin = new User();
                admin.setUsername(adminUsername);
                admin.setPassword(
                        passwordEncoder.encode(adminPassword)
                );
                admin.setRole(UserRole.ADMIN);

                userRepository.save(admin);

                System.out.println(
                        "AgentGuard initial admin user created."
                );
            }
        };
    }
}