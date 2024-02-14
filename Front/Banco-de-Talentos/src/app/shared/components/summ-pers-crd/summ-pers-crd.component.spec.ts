import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SummPersCrdComponent } from './summ-pers-crd.component';

describe('SummPersCrdComponent', () => {
  let component: SummPersCrdComponent;
  let fixture: ComponentFixture<SummPersCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SummPersCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SummPersCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
