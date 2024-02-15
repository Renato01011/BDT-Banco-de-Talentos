import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-exp-pers-crd',
  templateUrl: './exp-pers-crd.component.html',
  styleUrls: ['./exp-pers-crd.component.scss'],
})
export class ExpPersCrdComponent implements OnInit {

  newWorkExperienceDialog: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  openNewWorkExperienceDialog() {
    this.newWorkExperienceDialog = true;
  }

  hideNewWorkExperienceDialog() {
    this.newWorkExperienceDialog = false;
  }
}
