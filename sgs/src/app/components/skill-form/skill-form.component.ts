import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute,Router, RouterLink } from '@angular/router';
import { IForm } from '../i-form';
import { Skill } from '../../model/skill';
import { SkillService } from '../../service/skill.service';

@Component({
  selector: 'app-skill-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink,ReactiveFormsModule],
  templateUrl: './skill-form.component.html',
  styleUrls: ['./skill-form.component.css']
})
export class SkillFormComponent implements IForm<Skill>{

  constructor(
    private servico: SkillService,
    private router: Router,
    private route: ActivatedRoute
  ){ }
  ngOnInit(): void {
    
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.servico.getById(+id).subscribe({
        next: (resposta: Skill) => {
          this.registro = resposta;
          this.formSkill.patchValue(this.registro);
        }
      });
    }
  }

  registro: Skill = <Skill>{};

  formSkill = new FormGroup({
    nome: new FormControl<string | null>(null),
    descricao: new FormControl<string| null>(null),
    tipo: new FormControl<string| null>(null)
  });

  get form(){
    return this.formSkill.controls;
  }

  save(): void {
    this.registro = Object.assign(this.registro,this.formSkill.value);
    this.servico.save(this.registro).subscribe({
      complete: () => {
        this.router.navigate(['/skill']);
      }
    })
  }

}
