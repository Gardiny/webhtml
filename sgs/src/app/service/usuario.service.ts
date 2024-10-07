import { RespostaPaginada } from './../model/resposta-paginada';
import { Injectable } from '@angular/core';
import { IService } from './iservice';

import { RequisicaoPaginada } from '../model/requisicao-paginada';

import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { Usuario } from '../model/usuario';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService implements IService<Usuario>{
  constructor(
    private http: HttpClient
  ) { }
  apiUrl: string = environment.API_URL + '/usuario/';
  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada): Observable<RespostaPaginada<Usuario>> {
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
    return this.http.get<RespostaPaginada<Usuario>>(url);
  }
  getById(id: number): Observable<Usuario> {
    let url = this.apiUrl + id;
    return this.http.get<Usuario>(url);
  }
  save(objeto: Usuario): Observable<Usuario> {
    let url = this.apiUrl;
    if(objeto.id){
      return this.http.put<Usuario>(url, objeto);
    }else{
      return this.http.post<Usuario>(url, objeto);
    }
  }
  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
