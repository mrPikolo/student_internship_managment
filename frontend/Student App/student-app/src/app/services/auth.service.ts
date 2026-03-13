import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUser: User | null = null;
  private readonly API_URL = 'http://localhost:8085/api/auth';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<User> {

    return this.http.post<any>(`${this.API_URL}/login`, { username, password }).pipe(
      map(resp => {
        const u = resp?.user ?? resp;
        const user: User = {
          id: u.id,
          username: u.username,
          email: u.email ?? '',
          firstName: u.firstName ?? '',
          lastName: u.lastName ?? '',
          role: u.role ?? ''
        };
        this.currentUser = user;
        console.log('Logged in user:', user);
        localStorage.setItem('currentUser', JSON.stringify(user));
        return user;
      })
    );
  }

  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('currentUser');
  }

  isLoggedIn(): boolean {
    if (this.currentUser) return true;

    const storedUser = localStorage.getItem('currentUser');
    if (storedUser) {
      this.currentUser = JSON.parse(storedUser);
      return true;
    }
    return false;
  }

  getCurrentUser(): User | null {
    return this.currentUser || JSON.parse(localStorage.getItem('currentUser') || 'null');
  }
}
