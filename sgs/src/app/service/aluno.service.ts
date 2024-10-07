import { Injectable } from '@angular/core';
import { IService } from './iservice';
import { Aluno } from '../model/aluno';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { RespostaPaginada } from '../model/resposta-paginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AlunoService implements IService<Aluno> {
  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/aluno/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<Aluno>> {
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
    return this.http.get<RespostaPaginada<Aluno>>(url);
  }
  getById(id: number): Observable<Aluno> {
    let url = this.apiUrl + id;
    return this.http.get<Aluno>(url);
  }
  save(objeto: Aluno): Observable<Aluno> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Aluno>(url, objeto);
    } else {
      return this.http.post<Aluno>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
