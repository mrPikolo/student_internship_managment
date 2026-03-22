import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ScrollingModule } from '@angular/cdk/scrolling';

import { InternshipService } from '../../services/internship.service';
import { Internship } from '../../models/internship.model';

@Component({
  selector: 'app-internship-list',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterModule, ScrollingModule],
  templateUrl: './internship-list.component.html',
  styleUrl: './internship-list.component.css',
})
export class InternshipListComponent implements OnInit {
  internships: Internship[] = [];
  filteredInternships: Internship[] = [];
  paginatedInternships: Internship[] = [];

  filterForm!: FormGroup;

  // Pagination
  page = 1;
  pageSize = 5;

  constructor(
    private fb: FormBuilder,
    private service: InternshipService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.filterForm = this.fb.group({
      search: [''], // za kompaniju
      tech: [''], // za tehnologiju
    });

    this.service.getAll().subscribe((data) => {
      this.internships = data;
      this.filteredInternships = data;
      this.applyPagination();
    });

    // Subscribe na obje kontrole
    this.filterForm.valueChanges.pipe(debounceTime(300)).subscribe(() => {
      this.applyFilter();
    });
  }

  applyFilter() {
    const search = (this.filterForm.get('search')?.value || '').toLowerCase();
    const tech = (this.filterForm.get('tech')?.value || '').toLowerCase();

    this.filteredInternships = this.internships.filter((i) => {
      const matchesCompany = i.companyName.toLowerCase().includes(search);
      const matchesTech = tech
        ? i.technologies
            .split(',')
            .map((t) => t.trim().toLowerCase())
            .some((t) => t.includes(tech))
        : true;
      return matchesCompany && matchesTech;
    });

    this.page = 1;
    this.applyPagination();
  }

  applyPagination() {
    const start = (this.page - 1) * this.pageSize;
    const end = start + this.pageSize;
    this.paginatedInternships = this.filteredInternships.slice(start, end);
  }

  prevPage(): void {
    if (this.page > 1) {
      this.page--;
      this.applyPagination();
    }
  }

  nextPage(): void {
    if (
      this.page < Math.ceil(this.filteredInternships.length / this.pageSize)
    ) {
      this.page++;
      this.applyPagination();
    }
  }

  trackById(index: number, item: Internship) {
    return item.id;
  }

  parseTechnologies(tech: string): string[] {
    return tech
      ? tech
          .split(',')
          .map((t) => t.trim().toUpperCase())
          .filter((t) => t.length > 0)
      : [];
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }

  // unutar InternshipListComponent
  get totalPages(): number {
    return Math.ceil(this.filteredInternships.length / this.pageSize);
  }
}
