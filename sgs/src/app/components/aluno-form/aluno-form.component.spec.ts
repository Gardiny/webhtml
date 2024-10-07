import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunosFormComponent } from './aluno-form.component';

describe('AlunoFormComponent', () => {
  let component: AlunosFormComponent;
  let fixture: ComponentFixture<AlunosFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlunosFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AlunosFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
