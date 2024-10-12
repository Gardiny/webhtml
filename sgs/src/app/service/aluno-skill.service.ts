import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { AlunoSkill } from '../model/alunoskill';
import { RespostaPaginada } from '../model/resposta-paginada';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlunoSkillService {
  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/aluno-skill/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<AlunoSkill>> {
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
    return this.http.get<RespostaPaginada<AlunoSkill>>(url);
  }
  getById(id: number): Observable<AlunoSkill> {
    let url = this.apiUrl + id;
    return this.http.get<AlunoSkill>(url);
  }
  getByAluno(id: number): Observable<AlunoSkill[]> {
    let url = this.apiUrl + "alunoId/" + id;
    return this.http.get<AlunoSkill[]>(url);
  }
  getBySkill(id: number): Observable<AlunoSkill[]> {
    let url = this.apiUrl + "skillId/" + id;
    return this.http.get<AlunoSkill[]>(url);
  }
  save(objeto: AlunoSkill): Observable<AlunoSkill> {
    let url = this.apiUrl;
    if (objeto.aluno_id) {
      return this.http.put<AlunoSkill>(url, objeto);
    } else {
      return this.http.post<AlunoSkill>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }

}
