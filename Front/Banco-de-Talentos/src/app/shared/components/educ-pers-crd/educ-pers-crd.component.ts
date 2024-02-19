import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-educ-pers-crd',
  templateUrl: './educ-pers-crd.component.html',
  styleUrls: ['./educ-pers-crd.component.scss'],
})
export class EducPersCrdComponent implements OnInit {

  newEducationalExperienceDialog: boolean = false;
  editEducationalExperienceDialog: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  openEditEducationalExperienceDialog() {
    this.editEducationalExperienceDialog = true;
  }

  hideEditEducationalExperienceDialog() {
    this.editEducationalExperienceDialog = false;
  }

  openNewEducationalExperienceDialog() {
    this.newEducationalExperienceDialog = true;
  }

  hideNewEducationalExperienceDialog() {
    this.newEducationalExperienceDialog = false;
  }
}
