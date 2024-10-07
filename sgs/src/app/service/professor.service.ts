import { Injectable } from '@angular/core';
import { IService } from './iservice';
import { Professor } from '../model/professor';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { RespostaPaginada } from '../model/resposta-paginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService implements IService<Professor>{

  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/professor/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<Professor>> {
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
    return this.http.get<RespostaPaginada<Professor>>(url);
  }
  getById(id: number): Observable<Professor> {
    let url = this.apiUrl + id;
    return this.http.get<Professor>(url);
  }
  save(objeto: Professor): Observable<Professor> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Professor>(url, objeto);
    } else {
      return this.http.post<Professor>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
