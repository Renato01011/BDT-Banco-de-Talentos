import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'shared-prof-pers-crd',
  templateUrl: './prof-pers-crd.component.html',
  styleUrls: ['./prof-pers-crd.component.scss'],
})
export class ProfPersCrdComponent implements OnInit {
  resume: MenuItem[] = [];
  rating: number = 0;
  constructor() {}

  ngOnInit(): void {
    this.rating = 3;
    this.resume = [{ label: 'CV' }, { label: 'CV Fractal' }];
  }
}
