package com.toyota.dealership.testdrive;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTestDriveRequest(
        @NotBlank (message = "Customer name is required")
        @Size(min = 3, message = "Customer name must be at least 3 characters")
        String customerName,

        @NotBlank (message = "Vehicle model is required")
        String vehicleModel,

        @NotNull (message = "Appointment date is required")
        LocalDate appointmentDate,

        @NotNull (message = "Appointment time is required")
        LocalTime appointmentTime,

        @NotNull
        @Min(value = 15, message = "Duration must be at least 15 minutes")
        Integer durationMinutes,

        String notes
) {
}