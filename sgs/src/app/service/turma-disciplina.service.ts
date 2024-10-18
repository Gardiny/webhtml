import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { RespostaPaginada } from '../model/resposta-paginada';
import { RequisicaoPaginada } from '../model/requisicao-paginada';
import { Observable } from 'rxjs';
import { TurmaDisciplina } from '../model/turmadiscipllina';

@Injectable({
  providedIn: 'root'
})
export class TurmaDisciplinaService {
  constructor(
    private http: HttpClient
  ) { }

  apiUrl: string = environment.API_URL + '/turma-disciplina/';

  get(termoBusca?: string | undefined, paginacao?: RequisicaoPaginada | undefined): Observable<RespostaPaginada<TurmaDisciplina>> {
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
    return this.http.get<RespostaPaginada<TurmaDisciplina>>(url);
  }

  getById(id: number): Observable<TurmaDisciplina> {
    let url = this.apiUrl + id;
    return this.http.get<TurmaDisciplina>(url);
  }

  getByTurma(id: number): Observable<TurmaDisciplina[]> {
    let url = this.apiUrl + "turmaId/" + id;
    return this.http.get<TurmaDisciplina[]>(url);
  }

  save(objeto: TurmaDisciplina): Observable<TurmaDisciplina> {
    let url = this.apiUrl;
    if (objeto.turma_id) {
      return this.http.put<TurmaDisciplina>(url, objeto);
    } else {
      return this.http.post<TurmaDisciplina>(url, objeto);
    }
  }

  delete(id: number): Observable<void> {
    let url = this.apiUrl + id;
    return this.http.delete<void>(url);
  }
}
