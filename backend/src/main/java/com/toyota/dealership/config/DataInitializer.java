package com.toyota.dealership.config;

import com.toyota.dealership.testdrive.TestDrive;
import com.toyota.dealership.testdrive.TestDriveRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeTestDrives(
            TestDriveRepository repository
    ) {
        return args -> {

            repository.save(new TestDrive(
                    "Maria Garcia",
                    "2026 Toyota Camry",
                    LocalDate.of(2026, 8, 5),
                    LocalTime.of(10, 30),
                    30,
                    "Interested in hybrid model"
            ));

            repository.save(new TestDrive(
                    "David Kim",
                    "2026 Toyota RAV4",
                    LocalDate.of(2026, 8, 6),
                    LocalTime.of(13, 0),
                    45,
                    "Bringing spouse"
            ));

            repository.save(new TestDrive(
                    "Alicia Johnson",
                    "2026 Toyota Tacoma",
                    LocalDate.of(2026, 8, 7),
                    LocalTime.of(15, 30),
                    30,
                    null
            ));
        };
    }
}