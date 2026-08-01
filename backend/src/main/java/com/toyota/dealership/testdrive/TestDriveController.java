package com.toyota.dealership.testdrive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/test-drives")
public class TestDriveController {

    private final TestDriveService service;

    public TestDriveController(TestDriveService service) {
        this.service = service;
    }

    @GetMapping
    public List<TestDriveResponse> getTestDrives(@RequestParam(required = false) String search) {
        return service.getTestDrives(search);
    }

    

    @PostMapping
    public ResponseEntity<TestDriveResponse> scheduleTestDrive(@Valid @RequestBody CreateTestDriveRequest request) {
        
        TestDriveResponse response = service.scheduleTestDrive(request);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}