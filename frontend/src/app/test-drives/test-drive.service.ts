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

  getTestDrives(search?: string): Observable<TestDrive[]> {
    if (search !== undefined) {
      return this.http.get<TestDrive[]>(this.apiUrl, {
        params: { search }
      });
    }

    return this.http.get<TestDrive[]>(this.apiUrl);
  }
}