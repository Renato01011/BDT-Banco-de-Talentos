import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuItem } from 'primeng/api';
import { FrmValService } from '../../service/frm-val.service';

const gitHubRegEx = '^https://github.com/[a-zA-Z0-9-]+/?$';
const linkedInRegEx = '^https://www.linkedin.com/in/[a-zA-Z0-9-]+/?$';

@Component({
  selector: 'shared-prof-pers-crd',
  templateUrl: './prof-pers-crd.component.html',
  styleUrls: ['./prof-pers-crd.component.scss'],
})
export class ProfPersCrdComponent implements OnInit {
  resume: MenuItem[] = [];
  coins: any[] = [];
  rating: number = 0;

  selectedCoin: any = null;

  editSocialMediaDialog: boolean = false;
  editProfilePicture: boolean = false;
  editSalaryDialog: boolean = false;

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public redSocForm: FormGroup = this.fb.group({
    linkedin: [
      'https://www.linkedin.com/in/username',
      [Validators.pattern(linkedInRegEx)],
    ],
    github: ['https://github.com/usuario', [Validators.pattern(gitHubRegEx)]],
  });

  ngOnInit(): void {
    this.rating = 3;

    this.coins = [
      { name: 'Soles', code: '0' },
      { name: 'Dolares', code: '1' },
    ];

    this.resume = [{ label: 'CV' }, { label: 'CV Fractal' }];
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.redSocForm, field);
  }

  onSveRedSoc() {
    console.log(this.redSocForm.value);
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
