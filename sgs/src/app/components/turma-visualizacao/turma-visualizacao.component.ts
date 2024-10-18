import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { IList } from '../i-list';
import { Turma } from '../../model/turma';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { TurmaService } from '../../service/turma.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-turma-visualizacao',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './turma-visualizacao.component.html',
  styleUrls: ['./turma-visualizacao.component.css']
})
export class TurmaVisualizacaoComponent implements IList<Turma>, OnInit {
  constructor(
    private servico: TurmaService, 
    @Inject(PLATFORM_ID) private platformId: Object,
    private router: Router  // Injetando o Router para navegação
  ) { }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    } else {
      this.requisicaoPaginada.size = 5;
    }
    this.get();
  }

  registros: Turma[] = [];
  respostaPaginada: RespostaPaginada<Turma> = {} as RespostaPaginada<Turma>;
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

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

  onTurmaClick(turma: Turma): void {
    console.log('Turma selecionada:', turma);
    this.router.navigate(['/avaliacao'], { queryParams: { id: turma.id } });  // Navegação programática
  }
}
