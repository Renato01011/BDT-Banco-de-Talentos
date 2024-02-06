import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTalentComponent } from './new-talent.component';

describe('NewTalentComponent', () => {
  let component: NewTalentComponent;
  let fixture: ComponentFixture<NewTalentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewTalentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewTalentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
