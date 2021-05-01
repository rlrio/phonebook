package com.rlrio.phonebook;

import com.rlrio.phonebook.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    public static void main(String[] args) {
        log.info("Initializing Spring Boot Application.");
        SpringApplication.run(Application.class, args);
    }
}
