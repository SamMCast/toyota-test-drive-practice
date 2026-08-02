import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TestDrive, RescheduleTestDriveRequest, CreateTestDriveRequest } from './test-drive.model';

@Injectable({
  providedIn: 'root'
})
export class TestDriveService {

  private readonly http = inject(HttpClient);

  private readonly apiUrl = '/api/test-drives';

  getTestDrives(search?: string): Observable<TestDrive[]> {
    if (search !== undefined) {
      return this.http.get<TestDrive[]>(this.apiUrl, {
        params: { search }
      });
    }

    return this.http.get<TestDrive[]>(this.apiUrl);
  }

  cancelTestDriveAppointment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  rescheduleTestDriveAppointment(id: number, request: RescheduleTestDriveRequest): Observable<TestDrive> {
    return this.http.patch<TestDrive>(`${this.apiUrl}/${id}/schedule`, request);
  }

  scheduleTestDrive(request: CreateTestDriveRequest): Observable<TestDrive> {
    return this.http.post<TestDrive>(this.apiUrl, request);
  }

}