import { Component, inject, OnInit } from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import {CreateTestDriveRequest, RescheduleTestDriveRequest, TestDrive } from './test-drive.model';
import { TestDriveService } from './test-drive.service';


@Component({
  selector: 'app-test-drives',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './test-drives.component.html',
  styleUrl: './test-drives.component.scss'
})
export class TestDrivesComponent implements OnInit {

  private readonly testDriveService =
    inject(TestDriveService);

  testDrives: TestDrive[] = [];

  isLoading = true;

  errorMessage = '';

  editingTestDriveId: number | null = null;
  
  rescheduleDate = '';
  rescheduleTime = '';

  ngOnInit(): void {
    this.loadTestDrives();
  }

  loadTestDrives(search?: string): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.testDriveService
      .getTestDrives(search)
      .subscribe({
        next: testDrives => {
          this.testDrives = testDrives;
          this.isLoading = false;
        },
        error: () => {
          this.errorMessage =
            'Unable to load test drives.';

          this.isLoading = false;
        }
      });
  }

  onSearch(search: string): void {
    this.loadTestDrives(search);
  }

  onCancelTestDrive(id: number): void {
    this.testDriveService
      .cancelTestDriveAppointment(id)
      .subscribe({
        next: () => {
          this.testDrives = this.testDrives.filter(testDrive => testDrive.id !== id);
        },
        error: () => {
          this.errorMessage = 'Unable to cancel test drive appointment.';
        }
      });
    }

    startReschedule(testDrive: TestDrive): void {
      this.editingTestDriveId = testDrive.id;
      this.rescheduleDate = testDrive.appointmentDate;
      this.rescheduleTime = testDrive.appointmentTime;
    }

    cancelReschedule(): void {
      this.editingTestDriveId = null;
      this.rescheduleDate = '';
      this.rescheduleTime = '';
    }

    saveReschedule(id: number): void {

      const request: RescheduleTestDriveRequest = {
        appointmentDate: this.rescheduleDate,
        appointmentTime: this.rescheduleTime
      };

      this.testDriveService.rescheduleTestDriveAppointment(id, request).subscribe({
        next: (updatedTestDrive) => {
          this.testDrives = this.testDrives.map(testDrive =>
            testDrive.id === updatedTestDrive.id
              ? updatedTestDrive
              : testDrive
          );

          this.cancelReschedule();
        },
        error: () => {
          this.errorMessage = 'Unable to reschedule test drive appointment.';
        }

      });
    }

    scheduleTestDrive(form: NgForm): void {
      if (form.invalid) {
        return;
      }
      const request: CreateTestDriveRequest = {
        customerName: form.value.customerName,
        vehicleModel: form.value.vehicleModel,
        appointmentDate: form.value.appointmentDate,
        appointmentTime: form.value.appointmentTime,
        durationMinutes: form.value.durationMinutes,
        notes: form.value.notes
      };

      this.testDriveService.scheduleTestDrive(request).subscribe({
        next: (newTestDrive) => {
          this.testDrives = [...this.testDrives, newTestDrive];
          form.reset();
        },
        error: () => {
          this.errorMessage = 'Unable to schedule test drive.';
        }
      });
    }

}