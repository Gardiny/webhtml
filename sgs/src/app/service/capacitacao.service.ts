import { Injectable } from '@angular/core';
import { IService } from './iservice';
import { Capacitacao } from '../model/capacitacao';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { RespostaPaginada } from '../model/resposta-paginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CapacitacaoService implements IService<Capacitacao> {
  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/capacitacao/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<Capacitacao>> {
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
    return this.http.get<RespostaPaginada<Capacitacao>>(url);
  }
  getById(id: number): Observable<Capacitacao> {
    let url = this.apiUrl + id;
    return this.http.get<Capacitacao>(url);
  }
  save(objeto: Capacitacao): Observable<Capacitacao> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Capacitacao>(url, objeto);
    } else {
      return this.http.post<Capacitacao>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
