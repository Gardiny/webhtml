import { TestBed } from '@angular/core/testing';

import { AlunoSkillService } from './aluno-skill.service';

describe('AlunoSkillService', () => {
  let service: AlunoSkillService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlunoSkillService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
