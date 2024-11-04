import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AutoAvaliacaoService {
  private apiUrl: string = environment.API_URL + '/autoavaliacao/';

  constructor(private http: HttpClient) {}

  // Método para salvar uma autoavaliação
  save(autoAvaliacao: { aluno_id: number; skill_id: number; nota: number; disciplina_id: number; turma_id?: number | null }): Observable<any> {
    const url = this.apiUrl;
    return this.http.post<any>(url, autoAvaliacao);
  }
  
  // Método para obter uma autoavaliação por ID
  getById(id: number): Observable<any> {
    const url = this.apiUrl + id;
    return this.http.get<any>(url);
  }
}
