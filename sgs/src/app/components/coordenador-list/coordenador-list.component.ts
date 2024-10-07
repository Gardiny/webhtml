import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Coordenador } from '../../model/coordenador';
import { IList } from '../i-list';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { CoordenadorService } from '../../service/coordenador.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-coordenador-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './coordenador-list.component.html',
  styleUrl: './coordenador-list.component.css'
})
export class CoordenadorListComponent implements IList<Coordenador>, OnInit  {
  registros: Coordenador[] = [];
  respostaPaginada: RespostaPaginada<Coordenador> = {} as RespostaPaginada<Coordenador>;
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  constructor(private servico: CoordenadorService, @Inject(PLATFORM_ID) private platformId: Object) { }


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
      next: (resposta: RespostaPaginada<Coordenador>) => {
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
