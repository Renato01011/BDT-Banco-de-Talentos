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
  editSocialMediaDialog: boolean = false;
  editProfilePicture: boolean = false;
  editSalaryDialog: boolean = false;
  constructor() {}

  ngOnInit(): void {
    this.rating = 3;
    this.resume = [{ label: 'CV' }, { label: 'CV Fractal' }];
  }

  openEditProfilePicture() {
    this.editProfilePicture = true;
  }

  hideEditProfilePicture() {
    this.editProfilePicture = false;
  }

  openEditSocialMediaDialog() {
    this.editSocialMediaDialog = true;
  }

  hideEditSocialMediaDialog() {
    this.editSocialMediaDialog = false;
  }

  openEditSalaryDialog() {
    this.editSalaryDialog = true;
  }

  hideEditSalaryDialog() {
    this.editSalaryDialog = false;
  }
}
