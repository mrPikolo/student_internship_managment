import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
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

  constructor(private http: HttpClient) {}

  getCV(): Observable<CV | null> {
    // Mock CV data
    const mockCV: CV = {
      id: 1,
      personalInfo: {
        firstName: 'Ana',
        lastName: 'Anić',
        email: 'ana.anic@university.edu',
        phone: '+387 61 123 456',
        address: 'Sarajevo, BiH',
        dateOfBirth: new Date('2000-05-15')
      },
      education: [
        {
          institution: 'Univerzitet u Sarajevu',
          degree: 'Bachelor',
          fieldOfStudy: 'Computer Science and Informatics',
          startDate: new Date('2018-10-01'),
          endDate: new Date('2022-06-30'),
          grade: '9.2'
        }
      ],
      workExperience: [
        {
          company: 'Tech Startup',
          position: 'Junior Developer',
          startDate: new Date('2022-07-01'),
          endDate: new Date('2023-12-31'),
          description: 'Web application development using Angular and Node.js',
          technologies: ['Angular', 'Node.js', 'MongoDB']
        }
      ],
      skills: [
        { name: 'JavaScript', level: 'Advanced' },
        { name: 'TypeScript', level: 'Intermediate' },
        { name: 'Angular', level: 'Intermediate' },
        { name: 'HTML/CSS', level: 'Advanced' }
      ],
      interests: ['Web Development', 'Machine Learning', 'Open Source'],
      languages: [
        { name: 'Bosnian', level: 'Native' },
        { name: 'English', level: 'C1' },
        { name: 'German', level: 'B2' }
      ],
      internships: [
        {
          id: 1,
          company: 'Tech Startup',
          position: 'Intern',
          startDate: new Date('2021-06-01'),
          endDate: new Date('2021-08-31'),
          description: 'Summer internship working on frontend features'
        }
      ],
      imagePath: '/uploads/profile-ana.jpg',
      studentId: 1
    };

    return of(mockCV);
  }

  saveCV(cv: CV): Observable<CV> {
    // Mock save
    cv.id = cv.id || Date.now();
    cv.updatedAt = new Date();
    return of(cv);
  }

  updateCV(cv: CV): Observable<CV> {
    // Mock update
    cv.updatedAt = new Date();
    return of(cv);
  }

  deleteCV(): Observable<void> {
    // Mock delete
    return of(void 0);
  }
}
