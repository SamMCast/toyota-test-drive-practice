package com.toyota.dealership.testdrive;

import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.time.LocalTime;

public record RescheduleTestDriveRequest(
    
    @NotNull (message = "Appointment date is required")
    LocalDate appointmentDate,
    
    @NotNull (message = "Appointment time is required")
    LocalTime appointmentTime

) {
}