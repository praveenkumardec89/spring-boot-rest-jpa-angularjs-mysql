package nl.surance.user.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages={"nl.surance.user"})


public class SuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuranceApplication.class, args);
    }
}
