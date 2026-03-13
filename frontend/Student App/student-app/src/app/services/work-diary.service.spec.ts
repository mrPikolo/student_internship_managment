import { TestBed } from '@angular/core/testing';

import { WorkDiaryService } from './work-diary.service';

describe('WorkDiaryService', () => {
  let service: WorkDiaryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkDiaryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
