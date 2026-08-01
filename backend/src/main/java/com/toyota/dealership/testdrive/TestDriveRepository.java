package com.toyota.dealership.testdrive;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TestDriveRepository
        extends JpaRepository<TestDrive, Long> {
        List<TestDrive> findByCustomerNameContainingIgnoreCaseOrVehicleModelContainingIgnoreCase(String customerName, String vehicleModel);
}