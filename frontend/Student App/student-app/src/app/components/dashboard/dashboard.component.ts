import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { StudentService} from '../../services/student.service';
import { User } from '../../models/user.model';
import { Student } from '../../models/student.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  currentUser: User | null = null;
  student: Student | null = null;
  stats: any = null;

  menuItems = [
    {
      title: 'Create CV',
      description: 'Create your Europass CV',
      icon: '📄',
      route: '/cv',
      color: '#667eea'
    },
    {
      title: 'Browse Internships',
      description: 'Find and apply for internships',
      icon: '🔍',
      route: '/internships',
      color: '#764ba2'
    },
    {
      title: 'AI Recommendations',
      description: 'Personalized internship recommendations',
      icon: '🤖',
      route: '/ai-recommendations',
      color: '#f093fb'
    },
    {
      title: 'Work Diary',
      description: 'Track weekly activities',
      icon: '📓',
      route: '/work-diary',
      color: '#4facfe'
    }
  ];

  constructor(
    private authService: AuthService,
    private studentService: StudentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();

    this.studentService.getCurrentStudent().subscribe(student => {
      this.student = student;
    });

    this.studentService.getStudentStats().subscribe(stats => {
      this.stats = stats;
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  navigateTo(route: string): void {
    this.router.navigate([route]);
  }
}
