import { RespostaPaginada } from './../model/resposta-paginada';
import { Injectable } from '@angular/core';
import { IService } from './iservice';

import { RequisicaoPaginada } from '../model/requisicao-paginada';

import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { Turma } from '../model/turma';


@Injectable({
  providedIn: 'root'
})
export class TurmaService implements IService<Turma>{
  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/turma/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Turma>> {
    let url = this.apiUrl + "?";
    if (termoBusca){
      url += "termoBusca=" + termoBusca;
    }
    if (paginacao){
      url += "&page=" + paginacao.page;
      url += "&page=" +paginacao.size;
      paginacao.sort.forEach(campo => {
        url += "&sort=" + campo;
      });
    }else{
      url += "unpaged=true";
    }
    return this.http.get<RespostaPaginada<Turma>>(url);
  }
  getById(id: number): Observable<Turma> {
    let url = this.apiUrl + id;
    return this.http.get<Turma>(url);
  }
  save(objeto: Turma): Observable<Turma> {
    let url = this.apiUrl;
    if(objeto.id){
      return this.http.put<Turma>(url, objeto);
    }else{
      return this.http.post<Turma>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
