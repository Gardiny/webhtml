import { TestBed } from '@angular/core/testing';

import { TurmaDisciplinaService } from './turma-disciplina.service';

describe('TurmaDisciplinaService', () => {
  let service: TurmaDisciplinaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TurmaDisciplinaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
