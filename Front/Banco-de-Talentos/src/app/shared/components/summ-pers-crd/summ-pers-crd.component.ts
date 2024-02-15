import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-summ-pers-crd',
  templateUrl: './summ-pers-crd.component.html',
  styleUrls: ['./summ-pers-crd.component.scss'],
})
export class SummPersCrdComponent implements OnInit {
  editDescriptionDialog: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  openEditDescriptionDialog() {
    this.editDescriptionDialog = true;
  }

  hideEditDescriptionDialog() {
    this.editDescriptionDialog = false;
  }
}
