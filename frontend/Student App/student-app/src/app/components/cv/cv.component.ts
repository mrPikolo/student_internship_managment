import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CvService } from '../../services/cv.service';
import { CV } from '../../models/cv.model';
import { PersonalInfo } from '../../models/personal-info.model';
import { Education } from '../../models/education.model';
import { WorkExperience } from '../../models/work-experience.model';
import { Skill } from '../../models/skill.model';

@Component({
  selector: 'app-cv',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.css']
})
export class CvComponent implements OnInit {
  cvForm: FormGroup;
  cv: CV | null = null;
  loading = false;
  saving = false;
  imagePreview: string | null = null;

  skillLevels = ['Beginner', 'Intermediate', 'Advanced', 'Expert'];
  languageLevels = ['A1', 'A2', 'B1', 'B2', 'C1', 'C2'];

  constructor(
    private fb: FormBuilder,
    private cvService: CvService
  ) {
    this.cvForm = this.createForm();
  }

  ngOnInit(): void {
    this.loadCV();
  }

  createForm(): FormGroup {
    return this.fb.group({
      personalInfo: this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        phone: ['', Validators.required],
        address: ['', Validators.required],
        dateOfBirth: ['', Validators.required]
      }),
      education: this.fb.array([]),
      workExperience: this.fb.array([]),
      internships: this.fb.array([]),
      skills: this.fb.array([]),
      interests: this.fb.array([]),
      languages: this.fb.array([])
      ,imagePath: [''],
      studentId: [null]
    });
  }

  loadCV(): void {
    this.loading = true;
    this.cvService.getCV().subscribe({
      next: (cv) => {
        this.cv = cv;
        if (cv) {
          this.populateForm(cv);
        }
        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  populateForm(cv: CV): void {
    // Personal info
    this.cvForm.patchValue({
      personalInfo: cv.personalInfo
    });

    // Education
    const educationArray = this.cvForm.get('education') as FormArray;
    cv.education.forEach(edu => {
      educationArray.push(this.createEducationGroup(edu));
    });

    // Work experience
    const workArray = this.cvForm.get('workExperience') as FormArray;
    cv.workExperience.forEach(work => {
      workArray.push(this.createWorkExperienceGroup(work));
    });

    // Skills
    const skillsArray = this.cvForm.get('skills') as FormArray;
    cv.skills.forEach(skill => {
      skillsArray.push(this.createSkillGroup(skill));
    });

    // Interests
    const interestsArray = this.cvForm.get('interests') as FormArray;
    cv.interests.forEach(interest => {
      interestsArray.push(this.fb.control(interest));
    });

    // Languages
    const languagesArray = this.cvForm.get('languages') as FormArray;
    cv.languages.forEach(lang => {
      languagesArray.push(this.createLanguageGroup(lang));
    });

    // Internships
    const internshipsArray = this.cvForm.get('internships') as FormArray;
    if (cv.internships) {
      cv.internships.forEach(it => {
        internshipsArray.push(this.createInternshipGroup(it));
      });
    }

    // Image path / preview
    if (cv.imagePath) {
      this.cvForm.patchValue({ imagePath: cv.imagePath });
      this.imagePreview = cv.imagePath;
    }

    // studentId
    if ((cv as any).studentId) {
      this.cvForm.patchValue({ studentId: (cv as any).studentId });
    }
  }

  createEducationGroup(edu?: Education): FormGroup {
    return this.fb.group({
      institution: [edu?.institution || '', Validators.required],
      degree: [edu?.degree || '', Validators.required],
      fieldOfStudy: [edu?.fieldOfStudy || '', Validators.required],
      startDate: [edu?.startDate || '', Validators.required],
      endDate: [edu?.endDate || ''],
      grade: [edu?.grade || '']
    });
  }

  createWorkExperienceGroup(work?: WorkExperience): FormGroup {
    return this.fb.group({
      company: [work?.company || '', Validators.required],
      position: [work?.position || '', Validators.required],
      startDate: [work?.startDate || '', Validators.required],
      endDate: [work?.endDate || ''],
      description: [work?.description || '', Validators.required],
      technologies: [work?.technologies?.join(', ') || '']
    });
  }

  createInternshipGroup(it?: any): FormGroup {
    return this.fb.group({
      company: [it?.company || '', Validators.required],
      position: [it?.position || ''],
      startDate: [it?.startDate || ''],
      endDate: [it?.endDate || ''],
      description: [it?.description || '']
    });
  }

  createSkillGroup(skill?: Skill): FormGroup {
    return this.fb.group({
      name: [skill?.name || '', Validators.required],
      level: [skill?.level || 'Beginner', Validators.required]
    });
  }

  createLanguageGroup(lang?: any): FormGroup {
    return this.fb.group({
      name: [lang?.name || '', Validators.required],
      level: [lang?.level || 'B1', Validators.required]
    });
  }

  // Getters for FormArrays
  get educationArray(): FormArray {
    return this.cvForm.get('education') as FormArray;
  }

  get workExperienceArray(): FormArray {
    return this.cvForm.get('workExperience') as FormArray;
  }

  get skillsArray(): FormArray {
    return this.cvForm.get('skills') as FormArray;
  }

  get interestsArray(): FormArray {
    return this.cvForm.get('interests') as FormArray;
  }

  get languagesArray(): FormArray {
    return this.cvForm.get('languages') as FormArray;
  }

  get internshipsArray(): FormArray {
    return this.cvForm.get('internships') as FormArray;
  }

  // Add methods
  addEducation(): void {
    this.educationArray.push(this.createEducationGroup());
  }

  addWorkExperience(): void {
    this.workExperienceArray.push(this.createWorkExperienceGroup());
  }

  addInternship(): void {
    this.internshipsArray.push(this.createInternshipGroup());
  }

  addSkill(): void {
    this.skillsArray.push(this.createSkillGroup());
  }

  addInterest(): void {
    this.interestsArray.push(this.fb.control(''));
  }

  addLanguage(): void {
    this.languagesArray.push(this.createLanguageGroup());
  }

  // Remove methods
  removeEducation(index: number): void {
    this.educationArray.removeAt(index);
  }

  removeWorkExperience(index: number): void {
    this.workExperienceArray.removeAt(index);
  }

  removeInternship(index: number): void {
    this.internshipsArray.removeAt(index);
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result as string;
        this.cvForm.patchValue({ imagePath: this.imagePreview });
      };
      reader.readAsDataURL(file);
    }
  }

  removeSkill(index: number): void {
    this.skillsArray.removeAt(index);
  }

  removeInterest(index: number): void {
    this.interestsArray.removeAt(index);
  }

  removeLanguage(index: number): void {
    this.languagesArray.removeAt(index);
  }

  onSubmit(): void {
    if (this.cvForm.valid) {
      this.saving = true;

      const formValue = this.cvForm.value;
      const cv: CV = {
        ...this.cv,
        ...formValue,
        education: formValue.education.map((edu: any) => ({
          ...edu,
          startDate: new Date(edu.startDate),
          endDate: edu.endDate ? new Date(edu.endDate) : undefined
        })),
        workExperience: formValue.workExperience.map((work: any) => ({
          ...work,
          startDate: new Date(work.startDate),
          endDate: work.endDate ? new Date(work.endDate) : undefined,
          technologies: work.technologies ? work.technologies.split(',').map((t: string) => t.trim()) : []
        })),
        internships: formValue.internships ? formValue.internships.map((it: any) => ({
          ...it,
          startDate: it.startDate ? new Date(it.startDate) : undefined,
          endDate: it.endDate ? new Date(it.endDate) : undefined
        })) : [],
        imagePath: formValue.imagePath || this.imagePreview || undefined,
        studentId: formValue.studentId || (this.cv as any)?.studentId
      };

      const saveObservable = this.cv ? this.cvService.updateCV(cv) : this.cvService.saveCV(cv);

      saveObservable.subscribe({
        next: (savedCv) => {
          this.cv = savedCv;
          this.saving = false;
          alert('CV saved successfully!');
        },
        error: () => {
          this.saving = false;
          alert('Error saving CV!');
        }
      });
    } else {
      alert('Please fill in all required fields!');
    }
  }
}
