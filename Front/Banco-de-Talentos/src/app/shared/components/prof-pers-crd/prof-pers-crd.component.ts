import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuItem } from 'primeng/api';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { MasterService } from 'src/app/core/services/master/master.service';
import { CurrenciesModel } from '../../models/interfaces/master.interfaces';

const gitHubRegEx = '^https://github.com/[a-zA-Z0-9-]+/?$';
const linkedInRegEx = '^https://www.linkedin.com/in/[a-zA-Z0-9-]+/?$';

@Component({
  selector: 'shared-prof-pers-crd',
  templateUrl: './prof-pers-crd.component.html',
  styleUrls: ['./prof-pers-crd.component.scss'],
})
export class ProfPersCrdComponent implements OnInit {
  resume: MenuItem[] = [];
  rating: number = 0;

  coins: CurrenciesModel[] = [];

  selectedCoin: any = null;

  editSocialMediaDialog: boolean = false;
  editProfilePicture: boolean = false;
  editSalaryDialog: boolean = false;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private masterService: MasterService
  ) {}

  public redSocForm: FormGroup = this.fb.group({
    linkedin: [
      'https://www.linkedin.com/in/username',
      [Validators.pattern(linkedInRegEx)],
    ],
    github: ['https://github.com/usuario', [Validators.pattern(gitHubRegEx)]],
  });

  public salaryForm: FormGroup = this.fb.group({
    currency: ['', [Validators.required]],
    iAmount: ['', [Validators.required]],
    fAmount: ['', [Validators.required]],
  });

  ngOnInit(): void {
    this.rating = 3;

    this.resume = [{ label: 'CV' }, { label: 'CV Fractal' }];
    this.checkCurrencies();
  }

  private getCurrencies(): void {
    this.masterService.getCurrencies().subscribe({
      next: (skills) => {
        this.coins = skills;
      },
    });
  }

  public get isCurrencyListEmpty(): boolean {
    return !this.coins || this.coins.length === 0;
  }

  private get isCacheCurrenciesEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byCurrency.currencies ||
      this.masterService.cacheStorage.byCurrency.currencies.length === 0
    );
  }

  private checkCurrencies(): void {
    if (this.isCacheCurrenciesEmpty) {
      this.getCurrencies();
    } else {
      const cacheCurrencies =
        this.masterService.cacheStorage.byCurrency.currencies;
      this.coins = cacheCurrencies;
    }
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.redSocForm, field);
  }

  isValidSalaryField(field: string) {
    return this.fValidator.isValidField(this.salaryForm, field);
  }

  onSveSalary() {
    console.log(this.salaryForm.value);
  }

  onSveRedSoc() {
    console.log(this.redSocForm.value);
  }

  onPhotoUpload(event: any) {
    console.log('Upload');
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
    this.salaryForm.reset();
    this.editSalaryDialog = false;
  }
}
