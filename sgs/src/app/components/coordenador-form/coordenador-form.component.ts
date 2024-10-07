import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Coordenador } from '../../model/coordenador';
import { CoordenadorService } from '../../service/coordenador.service';
import { UsuarioService } from '../../service/usuario.service';
import { Usuario } from '../../model/usuario';
import { RespostaPaginada } from '../../model/resposta-paginada';

@Component({
  selector: 'app-coordenador-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './coordenador-form.component.html',
  styleUrls: ['./coordenador-form.component.css']
})
export class CoordenadorFormComponent implements IForm<Coordenador>{

  constructor(
    private servico: CoordenadorService,
    private router: Router,
    private route: ActivatedRoute,
    private servicoUsuario: UsuarioService
  ){ }
  ngOnInit(): void {

    this.servicoUsuario.get().subscribe({
      next: (resposta: RespostaPaginada<Usuario>) => {
        this.usuario = resposta.content;
      }
    })
    
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Coordenador) => {
          this.registro = resposta;
          this.formCoordenador.patchValue(this.registro);
        }
      });
    }
  }

  registro: Coordenador = <Coordenador>{};
  usuario: Usuario[] = []; 

  formCoordenador = new FormGroup({
    nome: new FormControl<string | null>(null),
    email: new FormControl<string | null> (null),
    telefone: new FormControl<string | null> (null),
    usuario_id: new FormControl<number | null> (null)
  });

  get form(){
    return this.formCoordenador.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro,this.formCoordenador.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/coordenador']);
      }
    })
  }

}
