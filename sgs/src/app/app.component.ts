import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { AlunoListComponent } from './components/aluno-list/aluno-list.component';
import { ProfessorListComponent } from './components/professor-list/professor-list.component';
import { SkillListComponent } from './components/skill-list/skill-list.component';
import { CoordenadorFormComponent } from './components/coordenador-form/coordenador-form.component'; 
import { ProfessorFormComponent } from './components/professor-form/professor-form.component';
import { UsuarioFormComponent } from "./components/usuario-form/usuario-form.component";
import { CapacitacaoFormComponent } from "./components/capacitacao-form/capacitacao-form.component";
import { TurmaFormComponent } from "./components/turma-form/turma-form.component";
import { DisciplinaFormComponent } from "./components/disciplina-form/disciplina-form.component";
import { SkillFormComponent } from "./components/skill-form/skill-form.component";
import { AlunoService } from './service/aluno.service';
import { Aluno } from './model/aluno';
import { RespostaPaginada } from './model/resposta-paginada';
import { RequisicaoPaginada } from './model/requisicao-paginada';
import { Turma } from './model/turma';
import { TurmaService } from './service/turma.service';
import { AlertaComponent } from "./components/alerta/alerta/alerta.component";
import { ILoginService, LoginService } from './service/login/i-login.service';
import { Usuario } from './model/usuario';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    AlunoListComponent,
    ProfessorListComponent,
    SkillListComponent,
    CoordenadorFormComponent,
    ProfessorFormComponent,
    RouterModule,
    UsuarioFormComponent,
    CapacitacaoFormComponent,
    TurmaFormComponent,
    DisciplinaFormComponent,
    SkillFormComponent,
    AlertaComponent,
],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'teste';
  usuario: Usuario = <Usuario>{};
  registros: Aluno[] = [];
  registros2: Turma[] = [];
  respostaPaginada: RespostaPaginada<Aluno> = {} as RespostaPaginada<Aluno>;
  requsicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  respostaPaginadaTurma: RespostaPaginada<Turma> = {} as RespostaPaginada<Turma>;
  requsicaoPaginadaTurma: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';

  totalAlunos: number = 0;
  alunosAtivos: number = 0;
  alunosConcluidos: number = 0;
  alunosDesistentes: number = 0;
  totalTurmas:number = 0;

  constructor(
    private router: Router, 
    private servico: AlunoService, 
    @Inject(PLATFORM_ID) private plataformaId: Object,private servicoTurma: TurmaService,
    @Inject(LoginService) private loginService: ILoginService 
  ) {
    this.loginService.usuarioAutenticado.subscribe({
      next: (usuario: Usuario) => {
        this.usuario = usuario;
      }
    });
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.plataformaId)) {
      this.requsicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    } else {
      this.requsicaoPaginada.size = 5;
    }
    this.getAluno();
    this.getTurma();
  }

  getAluno(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requsicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Aluno>) => {
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
        this.contar();  // Chama a função para contar alunos
      }
    });
  }
  getTurma(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servicoTurma.get(termoBusca, this.requsicaoPaginadaTurma).subscribe({
      next: (resposta: RespostaPaginada<Turma>) => {
        this.registros2 = resposta.content;
        this.respostaPaginadaTurma = resposta;
      }
    });
    this.contar()
  }

  contar(): void {
    this.totalAlunos = this.registros.length;
    this.totalTurmas = this.registros2.length
    this.alunosAtivos = this.registros.filter(aluno => aluno.status === 'ATIVO').length;
    this.alunosConcluidos = this.registros.filter(aluno => aluno.status === 'CONCLUIDO').length;
    this.alunosDesistentes = this.registros.filter(aluno => aluno.status === 'DESISTENTE').length;
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

  isLoggendIn(): boolean {
    return this.loginService.isLoggedIn();
  }

  isAdmin(): boolean {
    return this.usuario.papel == 'admin';
  }

  logout(): void {
    this.loginService.logout();
  }
  
}
