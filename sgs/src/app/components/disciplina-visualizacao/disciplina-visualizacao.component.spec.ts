import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplinaVisualizacaoComponent } from './disciplina-visualizacao.component';

describe('DisciplinaVisualizacaoComponent', () => {
  let component: DisciplinaVisualizacaoComponent;
  let fixture: ComponentFixture<DisciplinaVisualizacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisciplinaVisualizacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisciplinaVisualizacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
