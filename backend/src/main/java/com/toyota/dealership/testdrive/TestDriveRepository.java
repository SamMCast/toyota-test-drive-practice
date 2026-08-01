package com.toyota.dealership.testdrive;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDriveRepository
        extends JpaRepository<TestDrive, Long> {
}