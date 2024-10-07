import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { IList } from '../i-list';
import { Aluno } from '../../model/aluno';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlunoService } from '../../service/aluno.service';
import { DisciplinaService } from '../../service/disciplina.service'; // Importa o DisciplinaService
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-ranking-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './ranking-list.component.html',
  styleUrls: ['./ranking-list.component.css']
})
export class RankingListComponent implements IList<Aluno>, OnInit {
  registros: Aluno[] = []; // Lista de alunos com notas
  disciplinas: any[] = []; // Lista de disciplinas para seleção
  disciplinaSelecionada: string = ''; // ID da disciplina selecionada
  respostaPaginada: RespostaPaginada<Aluno> = {} as RespostaPaginada<Aluno>;
  requsicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  constructor(
    private servico: AlunoService, 
    private disciplinaService: DisciplinaService, // Adiciona o serviço de disciplinas
    @Inject(PLATFORM_ID) private plataformaId: Object
  ) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.plataformaId)) {
      const tamanhoPagina = localStorage.getItem('tamanhoPagina');
      this.requsicaoPaginada.size = tamanhoPagina ? parseInt(tamanhoPagina, 10) : 5;
    } else {
      this.requsicaoPaginada.size = 5;
    }

    // Carrega as disciplinas ao iniciar o componente
    this.loadDisciplinas();
  }

  // Método para carregar todas as disciplinas
  loadDisciplinas(): void {
    this.disciplinaService.get().subscribe({
      next: (resposta) => {
        this.disciplinas = resposta.content; // Ajusta para responder corretamente
      },
      error: (err) => {
        console.error('Erro ao carregar as disciplinas:', err);
      }
    });
  }

  // Método chamado ao selecionar uma disciplina
  onDisciplinaChange(): void {
    // Chama o método para buscar alunos que concluíram a disciplina selecionada
    if (this.disciplinaSelecionada) {
      // this.getAlunosByDisciplina(this.disciplinaSelecionada);
    }
  }

  // // Buscar alunos que concluíram a disciplina selecionada
  // getAlunosByDisciplina(disciplinaId: string): void {
  //   this.servico.getAlunosByDisciplina(disciplinaId, this.requsicaoPaginada).subscribe({
  //     next: (resposta: RespostaPaginada<Aluno>) => {
  //       // Assumindo que o campo 'nota' é parte do objeto 'Aluno'
  //       this.registros = resposta.content;
  //       this.respostaPaginada = resposta;
  //     },
  //     error: (err: any) => {
  //       console.error('Erro ao carregar os alunos:', err);
  //     }
  //   });
  // }

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
        console.error('Erro ao excluir o Aluno:', err);
      }
  });
  }
}
