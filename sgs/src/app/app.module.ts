// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { authInterceptor } from './interceptor/auth.interceptor'; // Verifique o caminho correto

@NgModule({
  declarations: [
    AppComponent,
    // ... outras declarações
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    // ... outros módulos
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useValue: authInterceptor, // Para funções de interceptor
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
