import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors, withXsrfConfiguration } from '@angular/common/http';
import { BasicLoginService } from './service/login/basic-login.service';
import { environment } from '../environments/environment.development';
import { LoginService } from './service/login/i-login.service';
import { JwtLoginService } from './service/login/jwt-login.service';
import { erroInterceptor } from './interceptor/erro.interceptor';
import { authInterceptor } from './interceptor/auth.interceptor';

export function loginServiceFactory() {

  const authType = environment.AUTH_TYPE;

  if (authType == 'basic') {
    return new BasicLoginService();
  } else if (authType == 'jwt') {
    return new JwtLoginService();
  } else {
    throw new Error('Tipo de autenticação deve ser "basic" ou "jwt"')
  }

}

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(withFetch(), withInterceptors([erroInterceptor, authInterceptor]), withXsrfConfiguration({cookieName: 'XSRF-TOKEN', headerName: 'X-XSRF-TOKEN'})), // Corrigido aqui
    { provide: LoginService, useFactory: loginServiceFactory}
  ],
}