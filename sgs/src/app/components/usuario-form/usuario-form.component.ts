import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Usuario } from '../../model/usuario';
import { UsuarioService } from '../../service/usuario.service';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements IForm<Usuario>{

  constructor(
    private servico: UsuarioService,
    private router: Router,
    private route: ActivatedRoute
  ){ }
  ngOnInit(): void {
    
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Usuario) => {
          this.registro = resposta;
          this.formUsuario.patchValue(this.registro);
        }
      });
    }
  }

  registro: Usuario = <Usuario>{};

  formUsuario = new FormGroup({
    nome_completo: new FormControl<string | null>(null),
    nome_usuario: new FormControl<string| null>(null),
    email: new FormControl<string | null>(null),
    papel: new FormControl<string | null>(null),
    senha: new FormControl<string | null>(null),
    ativo: new FormControl<boolean| null>(null)
  });

  get form(){
    return this.formUsuario.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro,this.formUsuario.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/usuario']);
      }
    })
  }

}
