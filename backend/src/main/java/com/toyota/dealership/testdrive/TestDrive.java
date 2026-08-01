package com.toyota.dealership.testdrive;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "test_drives")
public class TestDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String vehicleModel;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private Integer durationMinutes;

    private String notes;

    protected TestDrive() {
    }

    public TestDrive(
            String customerName,
            String vehicleModel,
            LocalDate appointmentDate,
            LocalTime appointmentTime,
            Integer durationMinutes,
            String notes
    ) {
        this.customerName = customerName;
        this.vehicleModel = vehicleModel;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public String getNotes() {
        return notes;
    }
}