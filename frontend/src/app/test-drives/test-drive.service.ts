import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TestDrive } from './test-drive.model';

@Injectable({
  providedIn: 'root'
})
export class TestDriveService {

  private readonly http = inject(HttpClient);

  private readonly apiUrl = '/api/test-drives';

  getTestDrives(): Observable<TestDrive[]> {
    return this.http.get<TestDrive[]>(this.apiUrl);
  }
}