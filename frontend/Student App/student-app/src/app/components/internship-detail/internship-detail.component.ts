import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Route, Router } from '@angular/router';

import { Internship } from '../../models/internship.model';
import { InternshipService } from '../../services/internship.service';

@Component({
  selector: 'app-internship-detail',
  standalone: true,
  imports: [],
  templateUrl: './internship-detail.component.html',
  styleUrl: './internship-detail.component.css'
})
export class InternshipDetailComponent implements OnInit{

  internship?: Internship;

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private service: InternshipService
  ) {}

   ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      this.service.getById(id).subscribe({
        next: (data) => this.internship = data,
        error: (err) => console.error('Error loading internship:', err)
      });
    }
  }

  // 🔹 string → niz + uppercase
  parseTechnologies(tech: string): string[] {
    return tech
      ? tech
          .split(',')
          .map(t => t.trim().toUpperCase())
          .filter(t => t.length > 0)
      : [];
  }

  // 🔹 akcija prijave (za sada dummy)
  apply(): void {
    alert('Application submitted!');
  }

  goBack(): void {
  this.router.navigate(['/internships']);
}
}
