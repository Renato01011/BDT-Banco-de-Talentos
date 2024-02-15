import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-pers-prof-crd',
  templateUrl: './pers-prof-crd.component.html',
  styleUrls: ['./pers-prof-crd.component.scss'],
})
export class PersProfCrdComponent implements OnInit {
  rating: number = 0;
  constructor() {}

  ngOnInit(): void {}
}
