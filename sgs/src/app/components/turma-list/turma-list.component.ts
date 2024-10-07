import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { TurmaService } from '../../service/turma.service';
import { Turma } from '../../model/turma';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { isPlatformBrowser } from '@angular/common';
import { IList } from '../i-list';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-turma-list',
  standalone: true,
  templateUrl: './turma-list.component.html',
  styleUrls: ['./turma-list.component.css'],
  imports: [CommonModule, FormsModule, RouterLink]
})
export class TurmaListComponent implements IList<Turma>, OnInit {
  registros: Turma[] = [];
  respostaPaginada: RespostaPaginada<Turma> = {} as RespostaPaginada<Turma>;
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';
  constructor(private servico: TurmaService, @Inject(PLATFORM_ID) private platformId: Object) { }
  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    } else {
      this.requisicaoPaginada.size = 5;
    }
    this.get();
  }
  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requisicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Turma>) => {
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
        console.error('Erro ao excluir a Turma:', err);
      }
    });
  }
}

