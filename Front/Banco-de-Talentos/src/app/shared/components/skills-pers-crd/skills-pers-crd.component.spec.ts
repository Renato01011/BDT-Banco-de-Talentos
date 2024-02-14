import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsPersCrdComponent } from './skills-pers-crd.component';

describe('SkillsPersCrdComponent', () => {
  let component: SkillsPersCrdComponent;
  let fixture: ComponentFixture<SkillsPersCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SkillsPersCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SkillsPersCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
