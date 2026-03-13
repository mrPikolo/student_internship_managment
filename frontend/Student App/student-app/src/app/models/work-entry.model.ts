export interface WorkEntry {
  id: number;
  date: Date;
  hours: number;
  activity: string;
  description: string;
  technologies?: string[];
  supervisorFeedback?: string;
  status: 'Draft' | 'Submitted' | 'Approved' | 'Rejected';
}
