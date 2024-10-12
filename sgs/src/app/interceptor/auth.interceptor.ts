// src/app/interceptor/auth.interceptor.ts
import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { JwtLoginService } from '../service/login/jwt-login.service';
import { HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> => {
  const jwtLoginService = inject(JwtLoginService);
  const token = jwtLoginService.getToken(); // Certifique-se de que o método getToken existe

  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  // Use next(req) para prosseguir com a requisição
  return next(req);
};
