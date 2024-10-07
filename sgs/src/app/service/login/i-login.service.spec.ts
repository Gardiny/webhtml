import { TestBed } from '@angular/core/testing';

import { ILoginService } from './i-login.service';

describe('ILoginService', () => {
  let service: ILoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ILoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
