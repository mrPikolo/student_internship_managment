export interface Student {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  university: string;
  faculty: string;
  yearOfStudy: number;
  gpa?: number;
  skills: string[];
  interests: string[];
  profilePicture?: string;
  createdAt: Date;
  updatedAt: Date;
}
