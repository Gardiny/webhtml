import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CapacitacaoListComponent } from './capacitacao-list.component';

describe('CapacitacaoListComponent', () => {
  let component: CapacitacaoListComponent;
  let fixture: ComponentFixture<CapacitacaoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CapacitacaoListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CapacitacaoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
