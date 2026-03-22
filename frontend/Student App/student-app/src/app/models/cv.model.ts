import { PersonalInfo } from './personal-info.model';
import { Education } from './education.model';
import { WorkExperience } from './work-experience.model';
import { Skill } from './skill.model';

export interface CV {
  id?: number;
  personalInfo: PersonalInfo;
  educations: Education[];
  workExperience: WorkExperience[];
  skills: Skill[];
  interests: string[];
  languages: { name: string; level: string }[];
  internships?: {
    id?: number;
    company: string;
    position?: string;
    startDate?: Date;
    endDate?: Date;
    description?: string;
  }[];
  imagePath?: string;
  studentId?: number;
  createdAt?: Date;
  updatedAt?: Date;
}
