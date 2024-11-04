import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SkillService } from '../../service/skill.service';
import { DisciplinaSkillDto } from '../../model/disciplina-skill';
import { AutoAvaliacaoService } from '../../service/autoavaliacao.service';
import { ILoginService, LoginService } from '../../service/login/i-login.service';
import { AlunoService } from '../../service/aluno.service';
import { TurmaAlunoService } from '../../service/turma-aluno.service';
import { Usuario } from '../../model/usuario';
import { Aluno } from '../../model/aluno';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Descricao} from '../../model/descricao';


@Component({
  selector: 'app-autoavaliacao',
  standalone: true,
  templateUrl: './autoavaliacao.component.html',
  styleUrls: ['./autoavaliacao.component.css'],
  imports: [CommonModule, FormsModule]
})
export class AutoAvaliacaoComponent implements OnInit {
  skills: Descricao[] = [];
  disciplinaId!: number;
  usuarioLogado: Usuario | null = null;
  alunoLogado: Aluno | null = null;
  turmaId: number | null = null;
  avaliacoes: { [skillId: number]: number } = {};
  nomeDisciplina: string = '';

  constructor(
    private route: ActivatedRoute,
    private skillService: SkillService,
    private autoAvaliacaoService: AutoAvaliacaoService,
    @Inject(LoginService) private loginService: ILoginService,
    private alunoService: AlunoService,
    private turmaAlunoService: TurmaAlunoService
  ) {}

  ngOnInit(): void {
    console.log('Inicializando AutoAvaliacaoComponent...');
    this.usuarioLogado = this.loginService.getUsuarioLogado();
    console.log('Usuário logado:', this.usuarioLogado);

    if (this.usuarioLogado) {
      this.obterAlunoLogado();
    } else {
      console.error('Nenhum usuário logado encontrado.');
    }
    
    this.disciplinaId = +this.route.snapshot.paramMap.get('disciplinaId')!;
    console.log('Disciplina ID encontrado:', this.disciplinaId);
    this.carregarSkills();
  }

  obterAlunoLogado(): void {
    console.log('Buscando aluno correspondente ao usuário logado...');
    this.alunoService.get('', {
      page: 0, size: 10,
      sort: []
    }).subscribe({
      next: (resposta) => {
        console.log('Resposta de AlunoService:', resposta);
        const aluno = resposta.content.find(aluno => aluno.usuario_nome_completo === this.usuarioLogado!.nome_completo);
        if (aluno) {
          this.alunoLogado = aluno;
          console.log('Aluno logado encontrado:', this.alunoLogado);
          this.obterTurmaAluno();
        } else {
          console.error('Aluno não encontrado para o usuário logado.');
        }
      },
      error: (err) => console.error('Erro ao buscar alunos:', err)
    });
  }

  obterTurmaAluno(): void {
    if (this.alunoLogado) {
      console.log('Buscando turma para o aluno logado com ID:', this.alunoLogado.id);
      this.turmaAlunoService.getByAluno(this.alunoLogado.id).subscribe({
        next: (turmas) => {
          console.log('Turmas encontradas para o aluno:', turmas);
          if (turmas.length > 0) {
            this.turmaId = turmas[0].turma_id;
            console.log('Turma ID associada ao aluno:', this.turmaId);
          } else {
            console.error('Nenhuma turma encontrada para o aluno logado.');
          }
        },
        error: (err) => console.error('Erro ao buscar turma do aluno:', err)
      });
    }
  }

  carregarSkills(): void {
    this.skillService.getSkillsByDisciplina(this.disciplinaId).subscribe({
        next: (disciplineSkills: DisciplinaSkillDto[]) => {
            this.nomeDisciplina = disciplineSkills.length > 0 ? disciplineSkills[0].disciplina_nome : 'Disciplina não encontrada';

            // Função para buscar todas as páginas de skills
            const fetchAllSkills = (page: number, accumulatedSkills: any[] = []): void => {
                const size = 50; // Número de itens por página, ajuste conforme necessário

                this.skillService.getAllSkills(page, size).subscribe({
                    next: (response) => {
                        // Adiciona a página atual de skills ao acumulado
                        accumulatedSkills.push(...response.content);

                        if (page < response.totalPages - 1) {
                            // Se houver mais páginas, chama novamente para a próxima página
                            fetchAllSkills(page + 1, accumulatedSkills);
                        } else {
                            // Quando todas as páginas foram carregadas, processa as skills
                            const skillMap = new Map<number, string>();
                            accumulatedSkills.forEach(skill => skillMap.set(skill.id, skill.descricao || "Descrição indisponível"));

                            // Adiciona a descrição ao disciplineSkills
                            this.skills = disciplineSkills.map(skill => ({
                                ...skill,
                                descricao: skillMap.get(skill.skill_id) || "Descrição não encontrada"
                            }));
                            console.log('Skills com descrições combinadas:', this.skills);
                        }
                    },
                    error: (err) => console.error('Erro ao buscar descrições das skills:', err)
                });
            };

            // Inicia a busca de todas as skills a partir da primeira página
            fetchAllSkills(0);
        },
        error: (err) => console.error('Erro ao buscar skills da disciplina:', err)
    });
  }

  avaliarSkill(skillId: number, nota: number): void {
    console.log("Avaliar")
    
    this.avaliacoes[skillId] = nota;
    console.log(this.avaliacoes)
    console.log(`Nota atribuída para a skill ${skillId}:`, nota);
    this.salvarAutoavaliacao(skillId); // Salva automaticamente após selecionar a nota
}

  salvarAutoavaliacao(skillId: number): void {
      if (!this.alunoLogado || !this.turmaId) {
          console.error('Aluno ou turma não definidos. Não é possível salvar a autoavaliação.');
          return;
      }

      const avaliacaoParaSalvar = {
          aluno_id: this.alunoLogado!.id,
          skill_id: skillId,
          nota: this.avaliacoes[skillId],
          data: new Date().toISOString(),
          disciplina_id: this.disciplinaId,
          turma_id: this.turmaId
      };

      console.log('Dados de autoavaliação a serem enviados:', avaliacaoParaSalvar);
      this.autoAvaliacaoService.save(avaliacaoParaSalvar).subscribe({
          next: () => {
              console.log(`Autoavaliação para skill ${skillId} salva com sucesso!`);
              alert(`Autoavaliação para skill ${skillId} salva com sucesso!`);
          },
          error: (err) => {
              console.error(`Erro ao salvar autoavaliação para skill ${skillId}:`, err);
              alert(`Erro ao salvar autoavaliação para skill ${skillId}: ${err.message}`);
          }
      });
  }

}
