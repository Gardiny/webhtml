import { TestBed } from '@angular/core/testing';

import { AutoavaliacaoService } from './autoavaliacao.service';

describe('AutoavaliacaoService', () => {
  let service: AutoavaliacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutoavaliacaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
