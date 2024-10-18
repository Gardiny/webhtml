import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliarListComponent } from './avaliar-list.component';

describe('AvaliarListComponent', () => {
  let component: AvaliarListComponent;
  let fixture: ComponentFixture<AvaliarListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvaliarListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliarListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
});

