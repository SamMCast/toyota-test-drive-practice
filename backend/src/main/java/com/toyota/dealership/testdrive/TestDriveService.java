package com.toyota.dealership.testdrive;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDriveService {

    private final TestDriveRepository repository;

    public TestDriveService(TestDriveRepository repository) {
        this.repository = repository;
    }

    public List<TestDriveResponse> getTestDrives(String search) {
        if (search != null && !search.isEmpty()) {
            TestDrive response =  repository.findByCustomerNameContainingIgnoreCaseOrVehicleModelContainingIgnoreCase(search, search);
        }
        else{
            
        }
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TestDriveResponse scheduleTestDrive(CreateTestDriveRequest request) {
        TestDrive testDrive = new TestDrive(
                request.customerName(),
                request.vehicleModel(),
                request.appointmentDate(),
                request.appointmentTime(),
                request.durationMinutes(),
                request.notes()
        );

        TestDrive savedTestDrive = repository.save(testDrive);
        return toResponse(savedTestDrive);
    }

    private TestDriveResponse toResponse(TestDrive testDrive) {
        return new TestDriveResponse(
                testDrive.getId(),
                testDrive.getCustomerName(),
                testDrive.getVehicleModel(),
                testDrive.getAppointmentDate(),
                testDrive.getAppointmentTime(),
                testDrive.getDurationMinutes(),
                testDrive.getNotes()
        );
    }
}