export interface TestDrive {
  id: number;
  customerName: string;
  vehicleModel: string;
  appointmentDate: string;
  appointmentTime: string;
  durationMinutes: number;
  notes: string | null;
}