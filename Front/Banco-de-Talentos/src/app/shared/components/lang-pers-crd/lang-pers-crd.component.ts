import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-lang-pers-crd',
  templateUrl: './lang-pers-crd.component.html',
  styleUrls: ['./lang-pers-crd.component.scss'],
})
export class LangPersCrdComponent implements OnInit {
  rating: number = 0;
  constructor() {}

  ngOnInit(): void {}
}
