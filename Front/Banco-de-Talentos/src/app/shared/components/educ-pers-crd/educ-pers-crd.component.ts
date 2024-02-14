import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'shared-educ-pers-crd',
  templateUrl: './educ-pers-crd.component.html',
  styleUrls: ['./educ-pers-crd.component.scss'],
})
export class EducPersCrdComponent implements OnInit {
  educationalExperienceDialog: boolean = false;
  constructor() {}

  ngOnInit(): void {}

  openNewEducationalExperienceDialog() {
    this.educationalExperienceDialog = true;
  }

  hideNewEducationalExperienceDialog() {
    this.educationalExperienceDialog = false;
  }
}
