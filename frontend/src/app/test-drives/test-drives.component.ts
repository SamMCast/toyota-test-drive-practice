import { Component, inject, OnInit } from '@angular/core';

import { TestDrive } from './test-drive.model';
import { TestDriveService } from './test-drive.service';

@Component({
  selector: 'app-test-drives',
  standalone: true,
  imports: [],
  templateUrl: './test-drives.component.html',
  styleUrl: './test-drives.component.scss'
})
export class TestDrivesComponent implements OnInit {

  private readonly testDriveService =
    inject(TestDriveService);

  testDrives: TestDrive[] = [];

  isLoading = true;

  errorMessage = '';

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
}