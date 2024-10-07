import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliarFormComponent } from './avaliar-form.component';

describe('AvaliarFormComponent', () => {
  let component: AvaliarFormComponent;
  let fixture: ComponentFixture<AvaliarFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvaliarFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliarFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
