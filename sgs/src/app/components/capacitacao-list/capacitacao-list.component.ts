import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IList } from '../i-list';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { Capacitacao } from '../../model/capacitacao';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CapacitacaoService } from '../../service/capacitacao.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-capacitacao-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './capacitacao-list.component.html',
  styleUrl: './capacitacao-list.component.css'
})
export class CapacitacaoListComponent implements IList<Capacitacao>{
  registros: Capacitacao[] = [];
  respostaPaginada: RespostaPaginada<Capacitacao> = {} as RespostaPaginada<Capacitacao>;
  requisicaoPagina: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca:string | undefined = '';

  constructor(private servico: CapacitacaoService, @Inject(PLATFORM_ID) private plataformaId: Object) { }

  ngOnInit(): void {
    if (isPlatformBrowser(this.plataformaId)){
      this.requisicaoPagina.size = parseInt(localStorage.getItem('tamanhoPagina') || '5')
    }else {
      this.requisicaoPagina.size = 5;
    }
    this.get();
  }
  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requisicaoPagina).subscribe({
      next:(resposta: RespostaPaginada<Capacitacao>) =>{
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
