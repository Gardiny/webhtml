import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Capacitacao } from '../../model/capacitacao';
import { CapacitacaoService } from '../../service/capacitacao.service';
import { CoordenadorService } from '../../service/coordenador.service';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { Coordenador } from '../../model/coordenador';

@Component({
  selector: 'app-capacitacao-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './capacitacao-form.component.html',
  styleUrls: ['./capacitacao-form.component.css']
})
export class CapacitacaoFormComponent implements IForm<Capacitacao>{

  constructor(
    private servico: CapacitacaoService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoCoordenador: CoordenadorService
  ){ }
  ngOnInit(): void {

    this.servicoCoordenador.get().subscribe({
      next: (resposta: RespostaPaginada<Coordenador>) => {
        this.coordenador = resposta.content;
      }
    })
    
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Capacitacao) => {
          this.registro = resposta;
          this.formCapacitacao.patchValue(this.registro);
        }
      });
    }
  }

  registro: Capacitacao = <Capacitacao>{};
  coordenador: Coordenador[] = [];

  formCapacitacao = new FormGroup({
    nome: new FormControl<string | null>(null),
    carga_horaria: new FormControl<string | null> (null),
    data_inicio: new FormControl<string | null> (null),
    data_fim: new FormControl<string | null> (null),
    coordenador_id: new FormControl<number | null>(null)
  });

  get form(){
    return this.formCapacitacao.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro,this.formCapacitacao.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/capacitacao']);
      }
    })
  }

}
