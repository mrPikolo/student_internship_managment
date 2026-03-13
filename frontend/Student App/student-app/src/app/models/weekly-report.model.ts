import { WorkEntry } from './work-entry.model';

export interface WeeklyReport {
  weekStart: Date;
  weekEnd: Date;
  entries: WorkEntry[];
  totalHours: number;
  status: 'Draft' | 'Submitted' | 'Approved';
}
