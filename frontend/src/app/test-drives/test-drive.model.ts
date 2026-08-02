export interface TestDrive {
  id: number;
  customerName: string;
  vehicleModel: string;
  appointmentDate: string;
  appointmentTime: string;
  durationMinutes: number;
  notes: string | null;
}

export interface RescheduleTestDriveRequest {
  appointmentDate: string;
  appointmentTime: string;
}

export interface CreateTestDriveRequest {
  customerName: string;
  vehicleModel: string;
  appointmentDate: string;
  appointmentTime: string;
  durationMinutes: number;
  notes: string;
}