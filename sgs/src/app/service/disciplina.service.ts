import { Injectable } from '@angular/core';
import { IService } from './iservice';
import { Disciplina } from '../model/disciplina';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { RespostaPaginada } from '../model/resposta-paginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DisciplinaService implements IService<Disciplina> {
  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/disciplina/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<Disciplina>> {
    let url = this.apiUrl + "?";
    if (termoBusca){
      url += "termoBusca" + termoBusca;
    }
    if (paginacao){
      url += "&page=" + paginacao.page;
      url += "&page=" + paginacao.size;
      paginacao.sort.forEach(campo => {
        url += "&sort=" +campo;
      });
  }else {
    url += "&unpaged=true";
  }
  return this.http.get<RespostaPaginada<Disciplina>>(url);
  }
  getById(id: number): Observable<Disciplina> {
    let url = this.apiUrl+ id;
    return this.http.get<Disciplina>(url);
  }
  save(objeto: Disciplina): Observable<Disciplina> {
    let url = this.apiUrl;
    if (objeto.id){
      return this.http.put<Disciplina>(url, objeto);
    }else{
      return this.http.post<Disciplina>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
