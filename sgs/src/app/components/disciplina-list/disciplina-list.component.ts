import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { DisciplinaService } from '../../service/disciplina.service';
import { Disciplina } from '../../model/disciplina';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { isPlatformBrowser } from '@angular/common';
import { IList } from '../i-list';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-disciplina-list',
  standalone: true,
  templateUrl: './disciplina-list.component.html',
  styleUrls: ['./disciplina-list.component.css'],
  imports: [CommonModule, FormsModule, RouterLink]
})
export class DisciplinaListComponent implements IList<Disciplina>, OnInit {
  registros: Disciplina[] = [];
  respostaPaginada: RespostaPaginada<Disciplina> = {} as RespostaPaginada<Disciplina>;
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';
  constructor(private servico: DisciplinaService, @Inject(PLATFORM_ID) private platformId: Object) { }
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
      next: (resposta: RespostaPaginada<Disciplina>) => {
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
        console.error('Erro ao excluir a Disciplina:', err);
      }
    });
  }
  irParaPagina(pagina: number): void {
    if (pagina >= 0 && pagina < this.respostaPaginada.totalPages) {
      this.requisicaoPaginada.page = pagina;
      this.get(this.termoBusca);
    }
  }

  // Gera o array de números de páginas para exibir no paginador
  paginas(): number[] {
    return Array(this.respostaPaginada.totalPages).fill(0).map((x, i) => i);
  }
}

