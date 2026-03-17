import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { Student } from '../models/student.model';
import { PersonalInfo } from '../models/personal-info.model';
import { Education } from '../models/education.model';
import { WorkExperience } from '../models/work-experience.model';
import { Skill } from '../models/skill.model';
import { CV } from '../models/cv.model';
@Injectable({
  providedIn: 'root'
})
export class CvService {
  private readonly API_URL = '/api/cv';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getCV(): Observable<CV> {
    const user = this.authService.getCurrentUser();
    const userId = user?.id;

    if (!userId) {
      return throwError(() => new Error('No logged-in user id available'));
    }

    const url = `http://localhost:8085${this.API_URL.replace('/cv', '/students')}/${userId}/user`;
    return this.http.get<Student>(url).pipe(
      map((student) => {
        const cv: CV = {
          personalInfo: {
            firstName: student.firstName,
            lastName: student.lastName,
            email: student.email,
            phone: '',
            address: '',
            dateOfBirth: new Date()
          },
          education: [
            {
              institution: student.university,
              degree: '',
              fieldOfStudy: student.faculty,
              startDate: new Date(),
              endDate: undefined,
              grade: student.gpa !== undefined && student.gpa !== null ? String(student.gpa) : undefined
            }
          ],
          workExperience: [],
          skills: [],
          interests: [],
          languages: [],
          internships: [],
          imagePath: undefined,
          studentId: student.id
        };
        return cv;
      })
    );
  }

  saveCV(cv: CV): Observable<CV> {
    cv.id = cv.id || Date.now();
    cv.updatedAt = new Date();
    return of(cv);
  }

  updateCV(cv: CV): Observable<CV> {
    cv.updatedAt = new Date();
    return of(cv);
  }

  deleteCV(): Observable<void> {
    return of(void 0);
  }
}
