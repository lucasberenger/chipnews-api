package com.chipnews.api.config;

import com.chipnews.api.entities.User;
import com.chipnews.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DEV CONFIG IS RUNNING...");

        // Creates a new user
        User user1 = new User(null, "Do Da Da", "dodada@io.com", "123456", "123456789");

        userRepository.save(user1);
    }


}
