package com.toyota.dealership.testdrive;

import java.time.LocalDate;
import java.time.LocalTime;

public record TestDriveResponse(
        Long id,
        String customerName,
        String vehicleModel,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        Integer durationMinutes,
        String notes
) {
}