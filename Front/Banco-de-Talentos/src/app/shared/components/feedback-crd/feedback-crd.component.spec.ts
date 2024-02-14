import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackCrdComponent } from './feedback-crd.component';

describe('FeedbackCrdComponent', () => {
  let component: FeedbackCrdComponent;
  let fixture: ComponentFixture<FeedbackCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
