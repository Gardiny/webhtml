import { TestBed } from '@angular/core/testing';

import { CapacitacaoService } from './capacitacao.service';

describe('CapacitacaoService', () => {
  let service: CapacitacaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CapacitacaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
