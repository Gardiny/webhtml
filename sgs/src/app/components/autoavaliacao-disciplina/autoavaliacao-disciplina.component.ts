import { Component, Inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { TurmaDisciplina } from '../../model/turmadiscipllina';
import { TurmaDisciplinaService } from '../../service/turma-disciplina.service';
import { CommonModule } from '@angular/common';
import { ILoginService, LoginService } from '../../service/login/i-login.service';
import { Usuario } from '../../model/usuario';
import { AlunoService } from '../../service/aluno.service';
import { Aluno } from '../../model/aluno';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { TurmaAlunoService } from '../../service/turma-aluno.service';
import { TurmaAluno } from '../../model/turmaAluno';

@Component({
  selector: 'app-autoavaliacao-disciplina',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './autoavaliacao-disciplina.component.html',
  styleUrl: './autoavaliacao-disciplina.component.css'
})
export class AutoAvaliacaoDisciplinaComponent {
  registros: TurmaDisciplina[] = [];
  usuarioLogado: Usuario | null = null;
  registrosAlunos: Aluno[] = [];
  respostaPaginada: RespostaPaginada<Aluno> = {} as RespostaPaginada<Aluno>;
  requsicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  constructor(
    private router: Router,
    private turmadiscplinaservice: TurmaDisciplinaService,
    private route: ActivatedRoute,
    @Inject(LoginService) private loginService: ILoginService,
    private alunoService: AlunoService,
    private turmaAlunoService: TurmaAlunoService
  ) {}

  ngOnInit(): void {
    // Obter o usuário logado
    this.usuarioLogado = this.loginService.getUsuarioLogado();
    console.log('Usuário logado:', this.usuarioLogado);

    // Buscar todos os alunos
    this.getAluno();
  }

  getAluno(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.alunoService.get(termoBusca, this.requsicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Aluno>) => {
        this.registrosAlunos = resposta.content;
        this.respostaPaginada = resposta;
        this.buscarAlunoLogado();
      }
    });
  }
  buscarAlunoLogado(): void {
    if (!this.usuarioLogado) {
      console.error('Usuário não está logado.');
      return;
    }
  
    // Filtrar o aluno com base no usuario_nome
    const alunoLogado = this.registrosAlunos.find(
      (aluno) => aluno.usuario_nome_completo === this.usuarioLogado!.nome_completo
    );
  
    if (!alunoLogado) {
      console.error('Aluno não encontrado para o usuário logado.');
      console.log('Usuário logado Nome:', this.usuarioLogado!.nome_completo);
      return;
    }
  
    console.log('Aluno logado encontrado:', alunoLogado);
  
    // Obter a turma do aluno logado
    this.turmaAlunoService.getByAluno(alunoLogado.id).subscribe({
      next: (turmas: TurmaAluno[]) => {
        if (turmas.length > 0) {
          console.log('Turmas encontradas para o aluno logado:', turmas);
  
          // Considerando que o aluno está apenas em uma turma
          const turmaId = turmas[0]?.turma_id;
          console.log(turmas[0].aluno_id)
          console.log('teste',turmaId);
          this.buscarDisciplinasPorTurma(turmaId);
        } else {
          console.error('Nenhuma turma encontrada para o aluno logado.');
        }
      },
      error: (err) => {
        console.error('Erro ao buscar turma do aluno:', err);
      }
    });
  }
  
  
  
  buscarDisciplinasPorTurma(turmaId: number): void {
    console.log('Buscando disciplinas para a turma ID:', turmaId);
    
    // Busca as disciplinas associadas à turma pelo ID
    this.turmadiscplinaservice.getByTurma(+turmaId).subscribe({
      next: (resposta: TurmaDisciplina[]) => {
        console.log('Disciplinas encontradas para a turma:', resposta);
  
        // Verificar se o array resposta é válido e contém disciplinas
        if (Array.isArray(resposta) && resposta.length > 0) {
          this.registros = resposta;
        } else {
          console.warn('Nenhuma disciplina encontrada na resposta:', resposta);
        }
      },
      error: (err) => {
        console.error('Erro ao buscar disciplinas:', err);
      }
    });
  }
  
  
}
