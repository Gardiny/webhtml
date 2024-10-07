import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { SkillService } from '../../service/skill.service';
import { Skill } from '../../model/skill';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { isPlatformBrowser } from '@angular/common';
import { IList } from '../i-list';
import { routes } from '../../app.routes';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-skill-list',
  standalone: true,
  templateUrl: './skill-list.component.html',
  styleUrls: ['./skill-list.component.css'],
  imports: [CommonModule, FormsModule, RouterLink]
})
export class SkillListComponent implements IList<Skill>, OnInit {
  registros: Skill[] = [];
  respostaPaginada: RespostaPaginada<Skill> = {} as RespostaPaginada<Skill>;
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';
  constructor(private servico: SkillService, @Inject(PLATFORM_ID) private platformId: Object) { }
  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    } else {
      this.requisicaoPaginada.size = 5;
    }
    this.get();
  }
  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requisicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Skill>) => {
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
        console.error('Erro ao excluir a skill:', err);
      }
    });
  }
}

