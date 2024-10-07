import { Component } from '@angular/core';
import { AlunoService } from '../../service/aluno.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Aluno } from '../../model/aluno';
import { CommonModule } from '@angular/common';
import { AlunoSkillService } from '../../service/aluno-skill.service';
import { AlunoSkill } from '../../model/alunoskill';

@Component({
  selector: 'app-aluno-visualizacao',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './aluno-visualizacao.component.html',
  styleUrl: './aluno-visualizacao.component.css'
})
export class AlunoVisualizacaoComponent {
  registro: Aluno = <Aluno>{};
  hardSkills: AlunoSkill[] = [];
  softSkills: AlunoSkill[] = [];

  constructor(
    private servico: AlunoService,
    private router: Router,
    private alunoSkillService: AlunoSkillService, 
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      // Busca o aluno pelo ID
      this.servico.getById(+id).subscribe({
        next: (resposta: Aluno) => {
          this.registro = resposta;
        }
      });

      // Busca as skills associadas ao aluno pelo ID
      this.alunoSkillService.getByAluno(+id).subscribe({
        next: (alunoSkills : AlunoSkill[]) => {
          // const alunoSkills = resposta.content.filter(skill => skill.aluno_id === +id); // Filtra as skills do aluno
          this.hardSkills = alunoSkills.filter(skill => skill.skill_tipo === 'HARD_SKILL');
          this.softSkills = alunoSkills.filter(skill => skill.skill_tipo === 'SOFT_SKILL');
        }
      });
    }
  }
}

