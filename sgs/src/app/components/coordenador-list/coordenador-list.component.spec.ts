import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordenadorListComponent } from './coordenador-list.component';

describe('CoordenadorListComponent', () => {
  let component: CoordenadorListComponent;
  let fixture: ComponentFixture<CoordenadorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoordenadorListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoordenadorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
