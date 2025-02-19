package com.chipnews.api.config;

import com.chipnews.api.entities.User;
import com.chipnews.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DEV CONFIG IS RUNNING...");


        // Creates a new user
        User user1 = new User(null, "Thiago", "thiago@io.com", "123456789", "Avenue Av - 123",null);
        user1.setPassword(passwordEncoder.encode("secret"));
        User user2 = new User(null, "Lucas", "lucas@io.com", "219988776655", "Street St - 425", null);
        user2.setPassword(passwordEncoder.encode("secret"));

        userRepository.saveAll(Arrays.asList(user1, user2));
    }


}
