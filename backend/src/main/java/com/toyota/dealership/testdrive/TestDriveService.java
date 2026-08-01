package com.toyota.dealership.testdrive;

import org.springframework.stereotype.Service;
import com.toyota.dealership.exception.TestDriveNotFoundException;

import java.util.List;

@Service
public class TestDriveService {

    private final TestDriveRepository repository;

    public TestDriveService(TestDriveRepository repository) {
        this.repository = repository;
    }

    public List<TestDriveResponse> getTestDrives(String search) {
        List<TestDrive> testDrives;

        if (search != null && !search.isBlank()) {
            String trimmedSearch = search.trim();

            testDrives = repository.findByCustomerNameContainingIgnoreCaseOrVehicleModelContainingIgnoreCase(trimmedSearch, trimmedSearch);
        }
        else{
            testDrives = repository.findAll();
        }
        return testDrives
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

    public void cancelTestDriveAppointment(Long id) {
        TestDrive testDrive = repository.findById(id)
                .orElseThrow(() -> new TestDriveNotFoundException(id));

        repository.delete(testDrive);
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