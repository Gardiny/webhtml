import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutoAvaliacaoDisciplinaComponent } from './autoavaliacao-disciplina.component';

describe('AutoAvaliacaoDisciplinaComponent', () => {
  let component: AutoAvaliacaoDisciplinaComponent;
  let fixture: ComponentFixture<AutoAvaliacaoDisciplinaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutoAvaliacaoDisciplinaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutoAvaliacaoDisciplinaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
