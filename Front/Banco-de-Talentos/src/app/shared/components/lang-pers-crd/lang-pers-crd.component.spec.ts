import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LangPersCrdComponent } from './lang-pers-crd.component';

describe('LangPersCrdComponent', () => {
  let component: LangPersCrdComponent;
  let fixture: ComponentFixture<LangPersCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LangPersCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LangPersCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
