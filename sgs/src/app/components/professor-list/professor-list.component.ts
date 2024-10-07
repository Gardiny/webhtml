import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { ProfessorService } from './../../service/professor.service';
import { Professor } from '../../model/professor';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { isPlatformBrowser } from '@angular/common';
import { IList } from '../i-list';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-professor-list',
  standalone: true,
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
  imports: [CommonModule, FormsModule, RouterLink]
})
export class ProfessorListComponent implements IList<Professor>, OnInit {
  registros: Professor[] = [];
  respostaPaginada: RespostaPaginada<Professor> = {} as RespostaPaginada<Professor>;
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  constructor(private servico: ProfessorService, @Inject(PLATFORM_ID) private platformId: Object) { }

  ngOnInit(): void {
    // Verifica se está no ambiente do navegador
    if (isPlatformBrowser(this.platformId)) {
      this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    } else {
      // Valor padrão caso o localStorage não esteja disponível
      this.requisicaoPaginada.size = 5;
    }
    
    this.get();
  }

  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requisicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Professor>) => {
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
        console.error('Erro ao excluir o professor:', err);
      }
    });
  }
}