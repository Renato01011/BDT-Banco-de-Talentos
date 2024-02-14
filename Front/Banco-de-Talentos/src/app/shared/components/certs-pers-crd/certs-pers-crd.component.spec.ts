import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CertsPersCrdComponent } from './certs-pers-crd.component';

describe('CertsPersCrdComponent', () => {
  let component: CertsPersCrdComponent;
  let fixture: ComponentFixture<CertsPersCrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CertsPersCrdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CertsPersCrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
