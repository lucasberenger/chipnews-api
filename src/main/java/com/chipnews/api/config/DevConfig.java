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


        // Creates a test user
        User test = new User(null, "Usuário", "email@io.com", "121231313", "Endereço - 1", null);
        test.setPassword(passwordEncoder.encode("test"));

        userRepository.save(test);
    }


}
