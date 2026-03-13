import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    const user = this.authService.getCurrentUser();
    // Allow only users with role 'STUDENT'
    if (user && (user as any).role && (user as any).role.toString().toUpperCase() === 'STUDENT') {
      return true;
    }
    // Not authorized - redirect to login
    this.router.navigate(['/login']);
    return false;
  }
}
