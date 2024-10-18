import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TurmaVisualizacaoComponent } from './turma-visualizacao.component';

describe('TurmaVisualizacaoComponent', () => {
  let component: TurmaVisualizacaoComponent;
  let fixture: ComponentFixture<TurmaVisualizacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TurmaVisualizacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TurmaVisualizacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
