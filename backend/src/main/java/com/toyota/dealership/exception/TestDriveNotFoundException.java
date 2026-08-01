package com.toyota.dealership.exception;

public class TestDriveNotFoundException extends RuntimeException {

    public TestDriveNotFoundException(Long id) {
        super("Test drive appointment not found with id: " + id);
    }
}