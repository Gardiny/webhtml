import { Injectable } from '@angular/core';
import { IService } from './iservice';
import { Skill } from '../model/skill';
import { Observable } from 'rxjs';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { RespostaPaginada } from '../model/resposta-paginada';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class SkillService implements IService<Skill> {

  constructor(private http: HttpClient) { }

  apiUrl: string = environment.API_URL + '/skill/';

  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<Skill>> {
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
    return this.http.get<RespostaPaginada<Skill>>(url);
  }

  getById(id: number): Observable<Skill> {
    let url = this.apiUrl + id;
    return this.http.get<Skill>(url);
  }

  save(objeto: Skill): Observable<Skill> {
    let url = this.apiUrl;
    if (objeto.id) {
      return this.http.put<Skill>(url, objeto);
    } else {
      return this.http.post<Skill>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
