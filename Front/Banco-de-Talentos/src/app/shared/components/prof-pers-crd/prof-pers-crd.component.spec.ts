import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfPersCrdComponent } from './prof-pers-crd.component';

describe('ProfPersCrdComponent', () => {
  let component: ProfPersCrdComponent;
  let fixture: ComponentFixture<ProfPersCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfPersCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfPersCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
