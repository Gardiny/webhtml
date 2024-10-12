import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Disciplina } from '../../model/disciplina';
import { DisciplinaService } from '../../service/disciplina.service';
import { Turma } from '../../model/turma';
import { Professor } from '../../model/professor';
import { TurmaService } from '../../service/turma.service';
import { ProfessorService } from '../../service/professor.service';
import { RespostaPaginada } from '../../model/resposta-paginada';

@Component({
  selector: 'app-disciplinas-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './disciplina-form.component.html',
  styleUrls: ['./disciplina-form.component.css']
})
export class DisciplinaFormComponent implements IForm<Disciplina>{

  constructor(
    private servico: DisciplinaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoTurma: TurmaService,
    private servicoProfessor: ProfessorService
  ){ }
  ngOnInit(): void {

    this.servicoTurma.get().subscribe({
      next: (resposta: RespostaPaginada<Turma>) => {
        this.turma = resposta.content;
      }
    })

    this.servicoProfessor.get().subscribe({
      next: (resposta: RespostaPaginada<Professor>) => {
        this.professor = resposta.content;
      }
    })
    
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Disciplina) => {
          this.registro = resposta;
          this.formDisciplina.patchValue(this.registro);
        }
      });
    }
  }

  registro: Disciplina = <Disciplina>{};
  turma: Turma[] = [];
  professor: Professor[] = [];

  formDisciplina = new FormGroup({
    codigo: new FormControl<string | null>(null),
    nome: new FormControl<string | null>(null),
    carga_horaria: new FormControl<string | null> (null),
    data_inicio: new FormControl<string | null> (null),
    data_fim: new FormControl<string | null> (null)
  });

  get form(){
    return this.formDisciplina.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro,this.formDisciplina.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/disciplina']);
      }
    })
  }

}
