import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersProfCrdComponent } from './pers-prof-crd.component';

describe('PersProfCrdComponent', () => {
  let component: PersProfCrdComponent;
  let fixture: ComponentFixture<PersProfCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersProfCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersProfCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
