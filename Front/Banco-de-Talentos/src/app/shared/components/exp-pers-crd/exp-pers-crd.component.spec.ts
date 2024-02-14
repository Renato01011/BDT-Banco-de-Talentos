import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpPersCrdComponent } from './exp-pers-crd.component';

describe('ExpPersCrdComponent', () => {
  let component: ExpPersCrdComponent;
  let fixture: ComponentFixture<ExpPersCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExpPersCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpPersCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
