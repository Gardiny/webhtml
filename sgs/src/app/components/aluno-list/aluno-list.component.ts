import { RespostaPaginada } from './../../model/resposta-paginada';
import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { IList } from '../i-list';
import { Aluno } from '../../model/aluno';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlunoService } from '../../service/aluno.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-aluno-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './aluno-list.component.html',
  styleUrl: './aluno-list.component.css'
})
export class AlunoListComponent implements IList<Aluno>, OnInit {
  registros: Aluno[] =[];
  respostaPaginada: RespostaPaginada<Aluno> = {} as RespostaPaginada<Aluno>;
  requsicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca:string | undefined = '';

  constructor(private servico: AlunoService, @Inject
  (PLATFORM_ID) private plataformaId: Object){ }

  ngOnInit(): void {
    if (isPlatformBrowser(this.plataformaId)){
      this.requsicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5')
    }else {
      this.requsicaoPaginada.size = 5;
    }
    this.get();
  }
  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requsicaoPaginada).subscribe({
      next:(resposta: RespostaPaginada<Aluno>) =>{
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
      }
    });
  }
  delete(id: number): void {
    this.servico.delete(id).subscribe({
      next: () => {
        this.registros = this.registros.filter(item => item.id !== id);
      },
      error: (err) => {
        console.error('Erro ao ecluir o Aluno:', err);
      }
  });
  }
}
