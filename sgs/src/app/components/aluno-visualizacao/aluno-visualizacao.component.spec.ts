import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunoVisualizacaoComponent } from './aluno-visualizacao.component';

describe('AlunoVisualizacaoComponent', () => {
  let component: AlunoVisualizacaoComponent;
  let fixture: ComponentFixture<AlunoVisualizacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlunoVisualizacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlunoVisualizacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
