import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

import { AlunoSkillService } from '../../service/aluno-skill.service';
import { SkillService } from '../../service/skill.service';
import { AlunoSkill } from '../../model/alunoskill';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { RespostaPaginada } from '../../model/resposta-paginada';

@Component({
  selector: 'app-ranking-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './ranking-list.component.html',
  styleUrls: ['./ranking-list.component.css']
})
export class RankingListComponent implements OnInit {
  registros: AlunoSkill[] = [];
  skills: any[] = [];
  skillSelecionada: string = '';
  requsicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();

  constructor(
    private alunoSkillService: AlunoSkillService, 
    private skillService: SkillService,
    @Inject(PLATFORM_ID) private plataformaId: Object
  ) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.plataformaId)) {
      const tamanhoPagina = localStorage.getItem('tamanhoPagina');
      this.requsicaoPaginada.size = tamanhoPagina ? parseInt(tamanhoPagina, 10) : 5;
    } else {
      this.requsicaoPaginada.size = 5;
    }

    // Carrega as skills ao iniciar o componente
    this.loadSkills();
  }

  // Carrega todas as skills
  loadSkills(): void {
    this.skillService.get().subscribe({
      next: (resposta) => {
        this.skills = resposta.content;
      },
      error: (err) => {
        console.error('Erro ao carregar as skills:', err);
      }
    });
  }

  // Método chamado ao selecionar uma skill
  onSkillChange(): void {
    if (this.skillSelecionada) {
      this.getAlunosBySkill(this.skillSelecionada);
    }
  }

  // Buscar alunos por skill selecionada e ordenar por nota
  getAlunosBySkill(skillId: string): void {
    // Ajuste a requisição paginada para ordenar por nota de forma decrescente
    this.requsicaoPaginada.sort = ['nota_final,desc'];

    this.alunoSkillService.get(skillId, this.requsicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<AlunoSkill>) => {
        // Ordena os alunos pela nota_final de forma decrescente
        this.registros = resposta.content.sort((a, b) => (b.nota_final || 0) - (a.nota_final || 0));
      },
      error: (err: any) => {
        console.error('Erro ao carregar os alunos por skill:', err);
      }
    });
  }
}
