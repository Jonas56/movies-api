package com.marsox.movies.config;

import com.marsox.movies.model.User;
import com.marsox.movies.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(IAuthService authService) {

        return args -> {
            log.info("Preloading " + authService.addNewUser(new User("Jonas", "Jonas56", "jonas@tesla.com", "pass123")));
        };
    }
}
