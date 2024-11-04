import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { TurmaAluno } from '../model/turmaAluno';
import { RespostaPaginada } from '../model/resposta-paginada';
import { RequisicaoPaginada } from '../model/requisicao-paginada';

@Injectable({
  providedIn: 'root'
})
export class TurmaAlunoService {

  apiUrl: string = environment.API_URL + '/turma-aluno/';

  constructor(private http: HttpClient) { }

  // Obter registros paginados com termo de busca opcional
  get(termoBusca?: string, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<TurmaAluno>> {
    let url = this.apiUrl + "?";
    
    if (termoBusca) {
      url += "termoBusca=" + termoBusca;
    }
    
    if (paginacao) {
      url += "&page=" + paginacao.page;
      url += "&size=" + paginacao.size;
      
      paginacao.sort.forEach(campo => {
        url += "&sort=" + campo;
      });
    } else {
      url += "&unpaged=true";
    }
    
    return this.http.get<RespostaPaginada<TurmaAluno>>(url);
  }

  // Obter TurmaAluno por turmaId
  getByTurma(turmaId: number): Observable<TurmaAluno[]> {
    const url = `${this.apiUrl}turmaId/${turmaId}`;
    return this.http.get<TurmaAluno[]>(url);
  }

  // Obter TurmaAluno por alunoId
  getByAluno(alunoId: number): Observable<TurmaAluno[]> {
    const url = `${this.apiUrl}alunoId/${alunoId}`;
    return this.http.get<TurmaAluno[]>(url);
  }

  // Obter TurmaAluno por turmaId e alunoId (chave composta)
  getById(turmaId: number, alunoId: number): Observable<TurmaAluno> {
    const url = `${this.apiUrl}${turmaId}/${alunoId}`;
    return this.http.get<TurmaAluno>(url);
  }

  // Criar ou atualizar um registro de TurmaAluno
  save(turmaAluno: TurmaAluno): Observable<TurmaAluno> {
    const url = this.apiUrl;
    
    if (turmaAluno.turma_id && turmaAluno.aluno_id) {
      return this.http.put<TurmaAluno>(`${url}${turmaAluno.turma_id}/${turmaAluno.aluno_id}`, turmaAluno);
    } else {
      return this.http.post<TurmaAluno>(url, turmaAluno);
    }
  }

  // Deletar um registro de TurmaAluno
  delete(turmaId: number, alunoId: number): Observable<void> {
    const url = `${this.apiUrl}${turmaId}/${alunoId}`;
    return this.http.delete<void>(url);
  }
}
