import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Professor } from '../../model/professor';
import { ProfessorService } from '../../service/professor.service';
import { UsuarioService } from '../../service/usuario.service';
import { Usuario } from '../../model/usuario';
import { RespostaPaginada } from '../../model/resposta-paginada';

@Component({
  selector: 'app-professor-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css']
})
export class ProfessorFormComponent implements IForm<Professor>{

  constructor(
    private servico: ProfessorService,
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
        next: (resposta: Professor) => {
          this.registro = resposta;
          this.formProfessor.patchValue(this.registro);
        }
      });
    }
  }

  registro: Professor = <Professor>{};
  usuario: Usuario[] = [];

  formProfessor = new FormGroup({
    nome: new FormControl<string | null>(null),
    email: new FormControl<string | null> (null),
    telefone: new FormControl<string | null> (null),
    usuario_id: new FormControl<number | null>(null)
  });

  get form(){
    return this.formProfessor.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro,this.formProfessor.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/professor']);
      }
    })
  }

}
