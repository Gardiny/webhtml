import { Routes } from '@angular/router';
import { CoordenadorFormComponent } from './components/coordenador-form/coordenador-form.component';
import { ProfessorFormComponent } from './components/professor-form/professor-form.component';
import { AlunoListComponent } from './components/aluno-list/aluno-list.component';
import { ProfessorListComponent } from './components/professor-list/professor-list.component';
import { SkillListComponent } from './components/skill-list/skill-list.component';
import { CoordenadorListComponent } from './components/coordenador-list/coordenador-list.component';
import { CapacitacaoListComponent } from './components/capacitacao-list/capacitacao-list.component';
import { DisciplinaFormComponent } from './components/disciplina-form/disciplina-form.component';
import { AlunoFormComponent } from './components/aluno-form/aluno-form.component';
import { SkillFormComponent } from './components/skill-form/skill-form.component';
import { CapacitacaoFormComponent } from './components/capacitacao-form/capacitacao-form.component';
import { DisciplinaListComponent } from './components/disciplina-list/disciplina-list.component';
import { TurmaListComponent } from './components/turma-list/turma-list.component';
import { TurmaFormComponent } from './components/turma-form/turma-form.component';
import { UsuarioListComponent } from './components/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { RankingListComponent } from './components/ranking-list/ranking-list.component';
import { AlunoVisualizacaoComponent } from './components/aluno-visualizacao/aluno-visualizacao.component';
import { LoginComponent } from './components/login/login.component';
import { AvaliarFormComponent } from './components/avaliar-form/avaliar-form.component';
import { AvaliarListComponent } from './components/avaliar-list/avaliar-list.component';
import { authGuard } from './service/auth.guard';

export const routes: Routes = [
  { path: '', canActivate: [authGuard], children: [
    { path: '', redirectTo: '/aluno', pathMatch: 'full' },
  { path: 'aluno', component: AlunoListComponent },
  { path: 'aluno/form', component: AlunoFormComponent},
  { path: 'capacitacao', component: CapacitacaoListComponent},
  { path: 'capacitacao/form', component: CapacitacaoFormComponent},
  { path: 'coordenador', component: CoordenadorListComponent},
  { path: 'coordenador/form', component: CoordenadorFormComponent},
  { path: 'disciplina', component: DisciplinaListComponent},
  { path: 'disciplina/form', component: DisciplinaFormComponent},
  { path: 'professor', component: ProfessorListComponent },
  { path: 'professor/form', component: ProfessorFormComponent},
  { path: 'skill', component: SkillListComponent },
  { path: 'skill/form', component: SkillFormComponent},
  { path: 'turma', component: TurmaListComponent},
  { path: 'turma/form', component: TurmaFormComponent},
  { path: 'usuario', component: UsuarioListComponent},
  { path: 'usuario/form', component: UsuarioFormComponent},
  { path: 'ranking', component: RankingListComponent},
  { path: 'aluno/visualizacao',component: AlunoVisualizacaoComponent},
  { path: 'avaliacao', component: AvaliarListComponent},
  { path: 'avaliacao/form', component: AvaliarFormComponent}
] },

{ path: 'login', component: LoginComponent },
{ path: '**', redirectTo: '' }];