import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducPersCrdComponent } from './educ-pers-crd.component';

describe('EducPersCrdComponent', () => {
  let component: EducPersCrdComponent;
  let fixture: ComponentFixture<EducPersCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EducPersCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EducPersCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
