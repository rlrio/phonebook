package se.rlrio.phonebook;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.rlrio.phonebook.service.impl.UserServiceImpl;

@SpringBootApplication
public class Application {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    public Application() {
    }

    public static void main(String[] args) {
        log.info("Initializing Spring Boot Application.");
        SpringApplication.run(Application.class, args);
    }
}
