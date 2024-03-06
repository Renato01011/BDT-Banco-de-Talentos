import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuItem } from 'primeng/api';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { MasterService } from 'src/app/core/services/master/master.service';
import { CurrenciesModel } from '../../models/interfaces/master.interfaces';
import { CustomTalent } from '../../models/interfaces/customTalent.interfaces';

const gitHubRegEx = '^https://github.com/[a-zA-Z0-9-]+/?$';
const linkedInRegEx = '^https://www.linkedin.com/in/[a-zA-Z0-9-]+/?$';

@Component({
  selector: 'shared-prof-pers-crd',
  templateUrl: './prof-pers-crd.component.html',
  styleUrls: ['./prof-pers-crd.component.scss'],
})
export class ProfPersCrdComponent implements OnInit {
  @Input()
  public customTalent?: CustomTalent;

  resume: MenuItem[] = [];

  coins: CurrenciesModel[] = [];

  editSocialMediaDialog: boolean = false;
  editProfilePicture: boolean = false;
  editSalaryDialog: boolean = false;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private masterService: MasterService
  ) {}

  public redSocForm: FormGroup = this.fb.group({
    linkedin: ['', [Validators.pattern(linkedInRegEx)]],
    github: ['', [Validators.pattern(gitHubRegEx)]],
  });

  public salaryForm: FormGroup = this.fb.group({
    currency: ['', [Validators.required]],
    iAmount: ['', [Validators.required]],
    fAmount: ['', [Validators.required]],
  });

  ngOnInit(): void {
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
    const linkedin = this.customTalent?.linkedin ?? '';
    const github = this.customTalent?.github ?? '';
    this.redSocForm.reset({ linkedin, github });
    this.editSocialMediaDialog = true;
  }

  hideEditSocialMediaDialog() {
    this.editSocialMediaDialog = false;
  }

  openEditSalaryDialog() {
    console.log(this.customTalent);
    const currency = this.coin.currency;
    const iAmount = this.customTalent?.initialSalary ?? '';
    const fAmount = this.customTalent?.finalSalary ?? '';
    this.salaryForm.reset({ currency, iAmount, fAmount });
    this.editSalaryDialog = true;
  }

  hideEditSalaryDialog() {
    this.salaryForm.reset();
    this.editSalaryDialog = false;
  }

  public get countryCity(): { country: string; city: string } {
    const result = { country: '', city: '' };
    if (!this.customTalent?.miscData) return result;
    const { miscData } = this.customTalent;
    const data = miscData.filter(
      (item) => item.name === 'PAIS' || item.name === 'CIUDAD'
    );
    for (const item of data) {
      if (item.name === 'PAIS') {
        result.country = item.description;
      } else if (item.name === 'CIUDAD') {
        result.city = item.description;
      }
    }
    return result;
  }

  public get workAs(): { name: string } {
    const result = { name: '' };
    if (!this.customTalent?.miscData) return result;
    const { miscData } = this.customTalent;
    const perfil = miscData.find((item) => item.name === 'PERFIL') ?? {
      description: '',
    };
    result.name = perfil.description;
    return result;
  }

  public get totFeedbacks(): number {
    const result = 0;
    if (!this.customTalent?.feedbacks) return result;
    return this.customTalent.feedbacks.length;
  }

  public get coin(): { id: number; currency: string } {
    const result = { id: 0, currency: '' };
    if (!this.customTalent?.miscData) return result;
    const { miscData } = this.customTalent;
    const curr = miscData.find((item) => item.name === 'MONEDA');
    if (curr) {
      return { id: curr.id, currency: curr.description };
    } else {
      return { id: 0, currency: '' };
    }
  }
}
