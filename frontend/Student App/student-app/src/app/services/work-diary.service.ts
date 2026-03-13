import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { WorkEntry } from '../models/work-entry.model';
import { WeeklyReport } from '../models/weekly-report.model';
@Injectable({
  providedIn: 'root'
})
export class WorkDiaryService {
  private readonly API_URL = '/api/work-diary';

  constructor(private http: HttpClient) {}

  getWeeklyReport(weekStart: Date): Observable<WeeklyReport> {
    // Mock data
    const mockEntries: WorkEntry[] = [
      {
        id: 1,
        date: new Date(weekStart.getTime() + 1 * 24 * 60 * 60 * 1000), // Monday
        hours: 8,
        activity: 'Frontend Development',
        description: 'Developed login component using Angular Material',
        technologies: ['Angular', 'TypeScript', 'Material UI'],
        status: 'Submitted'
      },
      {
        id: 2,
        date: new Date(weekStart.getTime() + 2 * 24 * 60 * 60 * 1000), // Tuesday
        hours: 7,
        activity: 'Backend API Development',
        description: 'Implemented REST API endpoint for authentication',
        technologies: ['Node.js', 'Express', 'JWT'],
        status: 'Submitted'
      },
      {
        id: 3,
        date: new Date(weekStart.getTime() + 3 * 24 * 60 * 60 * 1000), // Wednesday
        hours: 8,
        activity: 'Database Design',
        description: 'Designed database schema for user profiles',
        technologies: ['PostgreSQL', 'SQL'],
        status: 'Draft'
      }
    ];

    const weekEnd = new Date(weekStart.getTime() + 6 * 24 * 60 * 60 * 1000);
    const totalHours = mockEntries.reduce((sum, entry) => sum + entry.hours, 0);

    const report: WeeklyReport = {
      weekStart,
      weekEnd,
      entries: mockEntries,
      totalHours,
      status: 'Draft'
    };

    return of(report);
  }

  saveWorkEntry(entry: WorkEntry): Observable<WorkEntry> {
    // Mock save
    entry.id = entry.id || Date.now();
    return of(entry);
  }

  updateWorkEntry(entry: WorkEntry): Observable<WorkEntry> {
    // Mock update
    return of(entry);
  }

  deleteWorkEntry(entryId: number): Observable<void> {
    // Mock delete
    return of(void 0);
  }

  submitWeeklyReport(weekStart: Date): Observable<boolean> {
    // Mock submit
    return of(true);
  }

  getWorkHistory(): Observable<WeeklyReport[]> {
    // Mock history - return last 4 weeks
    const reports: WeeklyReport[] = [];
    const today = new Date();

    for (let i = 0; i < 4; i++) {
      const weekStart = new Date(today.getTime() - (i + 1) * 7 * 24 * 60 * 60 * 1000);
      weekStart.setDate(weekStart.getDate() - weekStart.getDay() + 1); // Monday

      reports.push({
        weekStart,
        weekEnd: new Date(weekStart.getTime() + 6 * 24 * 60 * 60 * 1000),
        entries: [],
        totalHours: 32 + Math.floor(Math.random() * 16), // Random hours between 32-48
        status: i === 0 ? 'Draft' : 'Submitted'
      });
    }

    return of(reports);
  }
}
