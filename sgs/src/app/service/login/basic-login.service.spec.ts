// import { TestBed } from '@angular/core/testing';

// import { BasicLoginService } from './basic-login.service';

// describe('BasicLoginService', () => {
//   let service: BasicLoginService;

//   beforeEach(() => {
//     TestBed.configureTestingModule({});
//     service = TestBed.inject(BasicLoginService);
//   });

//   it('should be created', () => {
//     expect(service).toBeTruthy();
//   });
// });

import { TestBed } from '@angular/core/testing';
import { BasicLoginService } from './basic-login.service';

describe('BasicLoginService', () => {
  let service: BasicLoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicLoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return true if user is logged in', () => {
    const usuarioMock = { nome_usuario: 'test', senha: '123' };
    sessionStorage.setItem('usuario', JSON.stringify(usuarioMock));
    
    expect(service.isLoggedIn()).toBeTrue();
  });

  it('should return false if user is not logged in', () => {
    sessionStorage.removeItem('usuario');
    expect(service.isLoggedIn()).toBeFalse();
  });

  it('should clear session storage on logout', () => {
    spyOn(sessionStorage, 'removeItem');
    service.logout();
    expect(sessionStorage.removeItem).toHaveBeenCalledWith('usuario');
  });
});
