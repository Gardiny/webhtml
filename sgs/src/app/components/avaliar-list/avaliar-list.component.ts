import { RespostaPaginada } from './../../model/resposta-paginada';
import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { IList } from '../i-list';
import { Aluno } from '../../model/aluno';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlunoService } from '../../service/aluno.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { TurmaDisciplinaService } from '../../service/turma-disciplina.service';
import { TurmaDisciplina } from '../../model/turmadiscipllina';

@Component({
  selector: 'app-avaliar-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './avaliar-list.component.html',
  styleUrls: ['./avaliar-list.component.css'] // Corrigido de "styleUrl" para "styleUrls"
})
export class AvaliarListComponent implements OnInit {
paginas(): any {
throw new Error('Method not implemented.');
}
irParaPagina(arg0: number) {
throw new Error('Method not implemented.');
}
  registros: Aluno[] = [];
  registroTurma: TurmaDisciplina[] = [];
  respostaPaginada: RespostaPaginada<Aluno> = {} as RespostaPaginada<Aluno>;
  requsicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  constructor(
    private servico: AlunoService,
    @Inject(PLATFORM_ID) private plataformaId: Object,
    private route: ActivatedRoute,
    private turmadiscplinaservice: TurmaDisciplinaService
  ) {}

  ngOnInit(): void {
    // Verifica se a aplicação está rodando no navegador
    if (isPlatformBrowser(this.plataformaId)) {
      this.requsicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    } else {
      this.requsicaoPaginada.size = 5;
    }
    // Chama o método para carregar os alunos
    this.get();
  }

  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requsicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Aluno>) => {
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
      },
      error: (err) => {
        console.error('Erro ao buscar alunos:', err);
      }
    });
  }

  delete(id: number): void {
    this.servico.delete(id).subscribe({
      next: () => {
        this.registros = this.registros.filter((item) => item.id !== id);
      },
      error: (err) => {
        console.error('Erro ao excluir o aluno:', err);
      }
    });
  }
}
