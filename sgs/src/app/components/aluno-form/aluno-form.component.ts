import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Aluno } from '../../model/aluno';
import { AlunoService } from '../../service/aluno.service';

@Component({
  selector: 'app-aluno-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.css']
})
export class AlunoFormComponent{

  constructor(
    private servico: AlunoService,
    private router: Router,
    private route: ActivatedRoute
  ){ }
  ngOnInit(): void {
    
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Aluno) => {
          this.registro = resposta;
          this.formAluno.patchValue(this.registro);
        }
      });
    }
  }

  registro: Aluno = <Aluno>{};

  formAluno = new FormGroup({
    nome: new FormControl<string | null>(null),
    email: new FormControl<string | null> (null),
    telefone: new FormControl<string | null> (null),
    foto: new FormControl<string |null> (null)
  });

  get form(){
    return this.formAluno.controls;
  }

  save(): void {

    this.registro = Object.assign(this.registro,this.formAluno.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/aluno']);
      }
    })
  }

  onFileSelected(event: any){
    const file = event.target.files[0];
    if (file){
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.formAluno.patchValue({ foto: reader.result?.toString().split(',')[1] });
      };
    }
  }
  
}
