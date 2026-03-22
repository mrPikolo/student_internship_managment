import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Internship } from '../models/internship.model';

@Injectable({
  providedIn: 'root'
})
export class InternshipService {

  private readonly API_URL  = 'http://localhost:8085/api/internships';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Internship[]> {
    return this.http.get<Internship[]>(this.API_URL);
  }

  getById(id: number): Observable<Internship> {
    return this.http.get<Internship>(`${this.API_URL}/${id}`);
  }
}
