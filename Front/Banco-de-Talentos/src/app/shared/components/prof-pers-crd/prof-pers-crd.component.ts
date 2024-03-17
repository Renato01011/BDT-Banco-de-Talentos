import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuItem } from 'primeng/api';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { MasterService } from 'src/app/core/services/master/master.service';
import { CurrenciesModel } from '../../models/interfaces/master.interfaces';
import { CustomTalent } from '../../models/interfaces/customTalent.interfaces';
import { EditInfoService } from '../../service/editInfo/edit-info.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';

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
  @Input()
  public selectedId?: number;
  @Output()
  public talentId = new EventEmitter<number>();

  public resume: MenuItem[] = [];
  public coins: CurrenciesModel[] = [];

  public isRecruiter: boolean = false;

  public editSocialMediaDialog: boolean = false;
  public editProfilePicture: boolean = false;
  public editSalaryDialog: boolean = false;

  photoText: string = 'Sube una nueva foto de perfil';
  photoDetailsText: string = 'PNG o JPG (max. 800x400px)';
  base64photo: string = '';
  photoUploaded: boolean = false;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private masterService: MasterService,
    private editInfoService: EditInfoService,
    private toastService: ToastService,
    private authService: AuthService
  ) {}

  public profileForm: FormGroup = this.fb.group({
    img: ['', [Validators.required]],
  });

  public redSocForm: FormGroup = this.fb.group({
    linkedin: ['', [Validators.required, Validators.pattern(linkedInRegEx)]],
    github: ['', [Validators.required, Validators.pattern(gitHubRegEx)]],
  });

  public salaryForm: FormGroup = this.fb.group(
    {
      currency: ['', [Validators.required]],
      iAmount: ['', [Validators.required]],
      fAmount: ['', [Validators.required]],
    },
    {
      validators: [
        this.fValidator.isFieldOneLessFieldTwo('iAmount', 'fAmount'),
      ],
    }
  );

  ngOnInit(): void {
    this.resume = [{ label: 'CV' }, { label: 'CV Fractal' }];
    this.checkCurrencies();
    this.isRecruiter = this.authService.isRecruiter;
  }

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  public async onPhotoUpload(event: any) {
    const photo: File = event.target.files[0];
    if (photo) {
      if (photo.type == 'image/jpeg' || photo.type == 'image/png') {
        const bmp = await createImageBitmap(photo);
        const { width, height } = bmp;
        bmp.close();
        if (width > 800 || height > 400) {
          this.toastService.addProperties(
            'error',
            'Ocurrio un error',
            'Las dimensiones de la imagen son demasiado grandes'
          );
          this.profileForm.controls['img'].setValue('');
          return;
        }
        this.photoUploaded = true;
        //this.photo = photo;
        const photoReader = new FileReader();
        photoReader.readAsDataURL(photo);
        photoReader.onloadend = () => {
          this.base64photo = photoReader.result as string;
        };
        this.photoText = 'Archivo Correctamente Subido';
        this.photoDetailsText = photo.name;
      } else {
        this.toastService.addProperties(
          'error',
          'Ocurrio un error',
          'Imagen no es PNG o JPG'
        );
        this.profileForm.controls['img'].setValue('');
        return;
      }
    } else {
      this.photoText = 'Error al Subir';
      this.toastService.addProperties(
        'error',
        'Ocurrio un error',
        'Error al subir imagen, vuelva a intentar'
      );
      this.profileForm.controls['img'].setValue('');
    }
  }

  public onSveProfile() {
    if (!this.onSaveForm(this.profileForm)) return;
    if (!this.selectedId) return;
    this.editInfoService
      .editTalentProfilePicture(
        { profilePicture: this.base64photo.split(',')[1] },
        this.selectedId
      )
      .subscribe({
        next: (resp) => {
          this.hideEditProfilePicture();
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
        },
      });
  }

  public onSveSalary() {
    if (!this.onSaveForm(this.salaryForm)) return;
    if (!this.selectedId) return;
    this.editInfoService
      .editTalentSalary(
        {
          idCoin: this.salaryForm.get('currency')!.value,
          initialSalary: this.salaryForm.get('iAmount')!.value,
          finalSalary: this.salaryForm.get('fAmount')!.value,
        },
        this.selectedId
      )
      .subscribe({
        next: (resp) => {
          this.hideEditSalaryDialog();
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
        },
      });
  }

  public onSveRedSoc() {
    if (!this.onSaveForm(this.redSocForm)) return;
    if (!this.selectedId) return;
    this.editInfoService
      .editTalentSocialLinks(
        {
          linkedin: this.redSocForm.get('linkedin')!.value,
          github: this.redSocForm.get('github')!.value,
        },
        this.selectedId
      )
      .subscribe({
        next: (resp) => {
          this.hidEditSocMediaDlg();
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
        },
      });
  }

  public isValidProfileField(field: string) {
    return this.fValidator.isValidField(this.profileForm, field);
  }

  public isValidSocField(field: string) {
    return this.fValidator.isValidField(this.redSocForm, field);
  }

  public isValidSalaryField(field: string) {
    return this.fValidator.isValidField(this.salaryForm, field);
  }

  public openEditProfilePicture() {
    this.editProfilePicture = true;
  }

  public opEditSocMediaDlg() {
    const linkedin = this.customTalent?.linkedin ?? '';
    const github = this.customTalent?.github ?? '';
    this.redSocForm.reset({ linkedin, github });
    this.editSocialMediaDialog = true;
  }

  public openEditSalaryDialog() {
    const currency = this.coin.id;
    const iAmount = this.customTalent?.initialSalary ?? '';
    const fAmount = this.customTalent?.finalSalary ?? '';
    this.salaryForm.reset({ currency, iAmount, fAmount });
    this.editSalaryDialog = true;
  }

  public hideEditSalaryDialog() {
    this.salaryForm.reset();
    this.editSalaryDialog = false;
  }

  public hideEditProfilePicture() {
    this.profileForm.reset();
    this.photoText = 'Sube una nueva foto de perfil';
    this.photoDetailsText = 'PNG o JPG (max. 800x400px)';
    this.photoUploaded = false;
    this.base64photo = '';
    this.editProfilePicture = false;
  }

  public hidEditSocMediaDlg() {
    this.redSocForm.reset();
    this.editSocialMediaDialog = false;
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

  private getCurrencies(): void {
    this.masterService.getCurrencies().subscribe({
      next: (coins) => {
        this.coins = coins;
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

  public getErrLinkField(field: string): string {
    let msg = this.fValidator.isRequiredErr(this.redSocForm, field);
    if (msg === null) {
      msg = 'Ingrese su link de LinkedIn.';
    }
    return msg;
  }
  public getErrGitField(field: string): string {
    let msg = this.fValidator.isRequiredErr(this.redSocForm, field);
    if (msg === null) {
      msg = 'Ingrese su link de GitHub.';
    }
    return msg;
  }

  public getErrFnAmountField(field: string): string {
    let msg = this.fValidator.isRequiredErr(this.salaryForm, field);
    if (msg === null) {
      msg = 'El monto final debe ser mayor que el monto inicial.';
    }
    return msg;
  }
}
