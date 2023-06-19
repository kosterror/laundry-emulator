package ru.tsu.hits.kosterror.laundryemulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LaundryEmulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaundryEmulatorApplication.class, args);
    }

}
