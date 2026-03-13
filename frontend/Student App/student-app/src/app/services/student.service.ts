import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Student } from '../models/student.model';
@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private readonly API_URL = '/api/students';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getCurrentStudent(): Observable<Student> {
    const user = this.authService.getCurrentUser();
    const userId = user?.id;

    if (!userId) {
      return throwError(() => new Error('No logged-in user id available'));
    }

    // Call backend endpoint: http://localhost:8085/api/students/{userId}/user
    const url = `http://localhost:8085${this.API_URL}/${userId}/user`;
    return this.http.get<Student>(url);
  }

  updateStudent(student: Partial<Student>): Observable<Student> {
    // Mock update
    const updatedStudent: Student = {
      id: 1,
      username: 'student1',
      email: 'ana.anic@university.edu',
      firstName: 'Ana',
      lastName: 'Anić',
      university: 'Univerzitet u Sarajevu',
      faculty: 'Elektrotehnički fakultet',
      yearOfStudy: 4,
      gpa: 9.2,
      skills: ['JavaScript', 'TypeScript', 'Angular', 'HTML', 'CSS', 'Node.js'],
      interests: ['Web Development', 'Machine Learning', 'Mobile Apps'],
      createdAt: new Date('2023-10-01'),
      updatedAt: new Date(),
      ...student
    };

    return of(updatedStudent);
  }

  getStudentStats(): Observable<{
    totalApplications: number;
    acceptedApplications: number;
    completedInternships: number;
    totalWorkHours: number;
  }> {
    // Mock stats
    const stats = {
      totalApplications: 5,
      acceptedApplications: 2,
      completedInternships: 1,
      totalWorkHours: 480
    };

    return of(stats);
  }

  uploadProfilePicture(file: File): Observable<{ url: string }> {
    // Mock upload
    const mockUrl = 'https://example.com/profile-pictures/student1.jpg';
    return of({ url: mockUrl });
  }
}
