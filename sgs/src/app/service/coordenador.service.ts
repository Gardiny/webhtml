import { Injectable } from '@angular/core';
import { IService } from './iservice';
import { Coordenador } from '../model/coordenador';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { RespostaPaginada } from '../model/resposta-paginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CoordenadorService implements IService<Coordenador>  {
  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/coordenador/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<Coordenador>> {
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
    return this.http.get<RespostaPaginada<Coordenador>>(url);
  }
  getById(id: number): Observable<Coordenador> {
    let url = this.apiUrl + id;
    return this.http.get<Coordenador>(url);
  }
  save(objeto: Coordenador): Observable<Coordenador> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Coordenador>(url, objeto);
    } else {
      return this.http.post<Coordenador>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
