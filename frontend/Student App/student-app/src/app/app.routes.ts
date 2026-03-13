import { Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'cv',
    loadComponent: () => import('./components/cv/cv.component').then(m => m.CvComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'internships',
    loadComponent: () => import('./components/internship-list/internship-list.component').then(m => m.InternshipListComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'internships/:id',
    loadComponent: () => import('./components/internship-detail/internship-detail.component').then(m => m.InternshipDetailComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'ai-recommendations',
    loadComponent: () => import('./components/ai-recommendations/ai-recommendations.component').then(m => m.AiRecommendationsComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'work-diary',
    loadComponent: () => import('./components/work-diary/work-diary.component').then(m => m.WorkDiaryComponent),
    canActivate: [AuthGuard]
  },
  { path: '**', redirectTo: '/dashboard' }
];
