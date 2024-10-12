import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Turma } from '../../model/turma';
import { TurmaService } from '../../service/turma.service';
import { CapacitacaoService } from '../../service/capacitacao.service';
import { Capacitacao } from '../../model/capacitacao';
import { RespostaPaginada } from '../../model/resposta-paginada';

@Component({
  selector: 'app-turma-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './turma-form.component.html',
  styleUrls: ['./turma-form.component.css']
})
export class TurmaFormComponent implements IForm<Turma>{

  constructor(
    private servico: TurmaService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoCapacitacao: CapacitacaoService
  ){ }
  ngOnInit(): void {

    this.servicoCapacitacao.get().subscribe({
      next: (resposta: RespostaPaginada<Capacitacao>) => {
        this.capacitacao = resposta.content;
      }
    })
    
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Turma) => {
          this.registro = resposta;
          this.formTurma.patchValue(this.registro);
        }
      });
    }
  }

  registro: Turma = <Turma>{};
  capacitacao: Capacitacao[] = [];

  formTurma = new FormGroup({
    nome: new FormControl<string | null>(null),
    quant_vagas: new FormControl<string| null>(null),
    data_inicio: new FormControl<string| null>(null),
    data_fim: new FormControl<string| null>(null),
    capacitacao_id: new FormControl<number| null> (null)
  });

  get form(){
    return this.formTurma.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro,this.formTurma.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/turma']);
      }
    })
  }

}
