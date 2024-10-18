import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { TurmaDisciplinaService } from '../../service/turma-disciplina.service';
import { TurmaDisciplina } from '../../model/turmadiscipllina';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-disciplina-visualizacao',
  standalone: true,
  imports: [CommonModule,RouterLink],
  templateUrl: './disciplina-visualizacao.component.html',
  styleUrls: ['./disciplina-visualizacao.component.css']  // Corrigido de "styleUrl" para "styleUrls"
})
export class DisciplinaVisualizacaoComponent implements OnInit {
  registros: TurmaDisciplina[] = [];

  constructor(
    private router: Router,
    private turmadiscplinaservice: TurmaDisciplinaService, 
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      // Busca as disciplinas associadas à turma pelo ID
      this.turmadiscplinaservice.getByTurma(+id).subscribe({
        next: (resposta: TurmaDisciplina[]) => {
          this.registros = resposta;
        },
        error: (err) => {
          console.error('Erro ao buscar disciplinas:', err);
        }
      });
    }
  }
}

