import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapacitacaoFormComponent } from './capacitacao-form.component';

describe('CapacitacaoFormComponent', () => {
  let component: CapacitacaoFormComponent;
  let fixture: ComponentFixture<CapacitacaoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CapacitacaoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CapacitacaoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
