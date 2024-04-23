import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MenuItem } from 'primeng/api';
import { OverlayPanel } from 'primeng/overlaypanel';
import { switchMap } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { MasterService } from 'src/app/core/services/master/master.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { CustomTalent } from '../../models/interfaces/customTalent.interfaces';
import { CurrenciesModel } from '../../models/interfaces/master.interfaces';
import { UserList } from '../../models/interfaces/userList.interfaces';
import { EditInfoService } from '../../service/editInfo/edit-info.service';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { UserService } from '../../service/user/user.service';
import { UtilsService } from '../../service/util/utils.service';

interface Favorite {
  name: string;
  code: string;
}

const gitHubRegEx = '^https://github.com/[a-zA-Z0-9-]+/?$';
const linkedInRegEx = '^https://www.linkedin.com/in/[a-zA-Z0-9-]+/?$';

@Component({
  selector: 'shared-prof-pers-crd',
  templateUrl: './prof-pers-crd.component.html',
  styleUrls: ['./prof-pers-crd.component.scss'],
})
export class ProfPersCrdComponent implements OnInit, OnChanges {
  @Input()
  public customTalent?: CustomTalent;
  @Input()
  public selectedId?: number;
  @Output()
  public talentId = new EventEmitter<number>();

  @ViewChild('fav') overlayPanelFvt!: OverlayPanel;

  public idUser?: number;
  selectedFavoriteId?: number;

  public resume: MenuItem[] = [];
  public coins: CurrenciesModel[] = [];

  favorites: UserList[] = [];
  public search: string = '';

  public isRecruiter: boolean = false;

  public editSocialMediaDialog: boolean = false;
  public editProfilePicture: boolean = false;
  public editSalaryDialog: boolean = false;

  photoText: string = 'Sube una nueva foto de perfil';
  photoDetailsText: string = 'PNG o JPG (max. 800x400px)';
  base64photo: string = '';
  photoUploaded: boolean = false;

  public feedbackMap = {
    '=0': 'Ningún feedback registrado',
    '=1': '# feedback',
    other: '# feedbacks',
  };

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private masterService: MasterService,
    private editInfoService: EditInfoService,
    private toastService: ToastService,
    private authService: AuthService,
    private userService: UserService,
    private utilsService: UtilsService,
    private confirmationService: ConfirmationService,
    private loaderService: LoaderService
  ) { }

  public profileForm: FormGroup = this.fb.group({
    img: ['', [Validators.required]],
  });

  public redSocForm: FormGroup = this.fb.group({
    linkedin: ['', [Validators.required, Validators.pattern(linkedInRegEx)]],
    github: ['', [Validators.required, Validators.pattern(gitHubRegEx)]],
  });

  //Tocar esto
  public salaryForm: FormGroup = this.fb.group(
    {
      currency: ['', [Validators.required]],
      iAmountRxH: [''],
      fAmountRxH: [''],
      iAmountPlanilla: [''],
      fAmountPlanilla: [''],
    },
    {
      validators: [
        this.fValidator.isFieldOneLessFieldTwo('iAmountRxH', 'fAmountRxH'),
        this.fValidator.isFieldOneLessFieldTwo(
          'iAmountPlanilla',
          'fAmountPlanilla'
        ),
      ],
    }
  );

  public addFileDialog: boolean = false;
  public fileText: string = 'Sube un archivo';
  public fileDetailsText: string = 'PDF (max. 5MB)';

  public enableEditViewPdf: boolean = false;
  public titleForCv = 'Curriculum Vitae';
  public paragraphForCV = 'Curriculum Vitae';
  public cvUploaded: boolean = false;

  public base64file?: string;

  public cvForm: FormGroup = this.fb.group({
    file: ['', [Validators.required]],
    fileType: ['PDF'],
  });

  ngOnInit(): void {
    this.resume = [
      { label: 'CV', command: (event) => this.btnCVhandler() },
      { label: 'CV Fractal' },
    ];

    this.isRecruiter = this.authService.isRecruiter;
    this.idUser = this.authService.idUser;
  }
  public onFileUpload(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      if (file.size > 50000000) {
        this.toastService.addProperties(
          'error',
          'Ocurrió un error',
          'Documento es demasiado pesado'
        );
        return;
      }
      if (file.type != 'application/pdf') {
        this.toastService.addProperties(
          'error',
          'Ocurrió un error',
          'Documento no es PDF'
        );
        return;
      }
      this.cvUploaded = true;

      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      fileReader.onloadend = () => {
        this.base64file = fileReader.result as string;
      };
      this.fileText = 'Archivo Correctamente Subido';
      this.fileDetailsText = file.name;
    } else {
      this.toastService.addProperties(
        'error',
        'Ocurrió un error',
        'Error al subir PDF, vuelva a intentar'
      );
    }
  }

  public isValidCvField(field: string) {
    return this.fValidator.isValidField(this.cvForm, field);
  }

  public onSveFile() {
    if (
      !this.onSaveForm(this.cvForm) ||
      !this.selectedId ||
      !this.base64file ||
      !this.customTalent?.resume
    ) {
      return;
    }
    const { fileType } = this.cvForm.value;
    const body = {
      nombre: this.extractFileName(this.fileDetailsText),
      tipoArchivo: fileType,
      archivo: this.base64file.split(',')[1],
    };
    this.loaderService.showLoader();
    this.editInfoService
      .updateResume(body, this.customTalent.resume.idDocument, this.selectedId)
      .subscribe({
        next: (resp) => {
          this.hideResumeDialog();
          this.toastService.addProperties(
            'success',
            'Se actualizo correctamente',
            resp.message
          );
          this.talentId.emit(Number(resp.id));
          this.loaderService.hideLoader();
        },
      });
  }

  extractFileName(fileName: string): string {
    const index = fileName.lastIndexOf('.pdf');
    if (index !== -1) {
      return fileName.substring(0, index);
    } else {
      return fileName;
    }
  }

  btnCVhandler() {
    this.enableEditViewPdf = false;
    this.titleForCv = 'Curriculum Vitae';
    this.paragraphForCV = 'Curriculum Vitae';

    this.openResumeDialog();
  }

  onBtnUpdatePdf() {
    this.enableEditViewPdf = true;
    this.titleForCv = 'Editar Curriculum Vitae';
    this.paragraphForCV = 'Sube tu nuevo Curriculum Vitae.';
  }

  openPdfInNewTab() {
    if (!this.customTalent?.resume) return;
    const pdfData = this.customTalent.resume.document;
    this.utilsService.openPdfInNewTab(pdfData);
  }

  public openResumeDialog() {
    this.addFileDialog = true;
  }

  public hideResumeDialog() {
    this.cvForm.reset({ fileType: 'PDF' });
    this.fileText = 'Sube un archivo';
    this.fileDetailsText = 'PDF (max. 5MB)';
    this.cvUploaded = false;
    this.addFileDialog = false;
  }

  openWhatsApp() {
    const phoneNum = this.customTalent?.phone
      .replace(/\s/g, '')
      .replace(/-/g, '');
    const whatsAppUrl = `https://wa.me/${phoneNum}`;
    window.open(whatsAppUrl, '_blank');
  }

  onSelectedFavorite(id: number, search: string, name: string) {
    if (this.isAvailableToEdit()) {
      this.editTalentOnUserListConfirm(id, search, name);
    } else {
      this.addTalentToUserListConfirm(id, search, name);
    }
  }

  onOpenOverlayPanel(event: any) {
    this.checkFavorites();
    this.search = '';
    this.isAvailableToEdit();
    this.overlayPanelFvt.toggle(event);
  }

  private getFavorites(userId: number) {
    this.loaderService.showLoader();
    this.userService.getUserLists(userId).subscribe({
      next: (favorites) => {
        this.favorites = favorites;
        this.loaderService.hideLoader();
      },
    });
  }

  public get isCacheFavoritesEmpty(): boolean {
    return (
      !this.userService.favoritesCache ||
      this.userService.favoritesCache.length === 0
    );
  }

  private checkFavorites(): void {
    if (!this.authService.idUser) return;
    if (this.isCacheFavoritesEmpty) {
      this.getFavorites(this.authService.idUser);
    } else {
      this.favorites = this.userService.favoritesCache;
    }
  }

  onSearchFavorites(search: string) {
    this.search = search;
  }

  onCreateNewFavorite(search: string, name: string) {
    this.confirmationService.confirm({
      header: 'Advertencia',
      message: `¿Desea agregar a "${name}" a su lista de "${search}"?`,
      icon: 'pi pi-info-circle',
      accept: () => {
        if (!this.selectedId || !this.idUser) return;
        this.loaderService.showLoader();
        this.userService
          .addNewList(this.idUser, search)
          .pipe(
            switchMap(({ idUserList }) =>
              this.userService.addTalentToList(idUserList, this.selectedId!)
            )
          )
          .subscribe({
            next: (resp) => {
              this.userService.favoritesCache = [];
              this.overlayPanelFvt.hide();
              this.toastService.addProperties(
                'success',
                'Se agrego correctamente',
                resp.message
              );
              this.talentId.emit(this.selectedId);
              this.loaderService.hideLoader();
            },
          });
      },
    });
  }

  private addTalentToUserListConfirm(id: number, search: string, name: string) {
    this.confirmationService.confirm({
      header: 'Advertencia',
      message: `¿Desea agregar a "${name}" a su lista de "${search}"?`,
      icon: 'pi pi-info-circle',

      accept: () => {
        if (!this.selectedId) return;
        this.addTalentToUserList(id, this.selectedId);
      },
    });
  }

  private editTalentOnUserListConfirm(
    id: number,
    search: string,
    name: string
  ) {
    this.confirmationService.confirm({
      header: 'Advertencia',
      message: `¿Está seguro de que desea agregar a "${name}" a su lista de "${search}"?`,
      icon: 'pi pi-info-circle',

      accept: () => {
        const idListUserTalent = this.getIdListUserTalent();
        if (!idListUserTalent) return;
        this.editTalentOnUserList(idListUserTalent, id);
      },
    });
  }

  private isAvailableToEdit(): boolean {
    if (this.customTalent?.userListTalent) {
      const idFa = this.customTalent.userListTalent.idListUser;
      this.selectedFavoriteId = idFa;
      return true;
    } else {
      this.selectedFavoriteId = undefined;
      return false;
    }
  }

  private getIdListUserTalent(): number | null {
    if (this.customTalent?.userListTalent) {
      const id = this.customTalent.userListTalent.idListUserTalent;
      return id;
    }
    return null;
  }

  private addTalentToUserList(idUserList: number, idTalent: number) {
    this.loaderService.showLoader();
    this.userService.addTalentToList(idUserList, idTalent).subscribe({
      next: (resp) => {
        this.overlayPanelFvt.hide();
        this.toastService.addProperties(
          'success',
          'Se agrego correctamente',
          resp.message
        );
        this.talentId.emit(this.selectedId);
        this.loaderService.hideLoader();
      },
    });
  }

  private editTalentOnUserList(idListUserTalent: number, newListId: number) {
    this.userService.editTalentUserList(idListUserTalent, newListId).subscribe({
      next: (resp) => {
        this.overlayPanelFvt.hide();
        this.toastService.addProperties(
          'success',
          'Se edito correctamente',
          resp.message
        );
        this.talentId.emit(this.selectedId);
      },
    });
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
    this.loaderService.showLoader();
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
          this.loaderService.hideLoader();
        },
      });
  }
  //Tocar esto
  public onSveSalary() {
    if (!this.onSaveForm(this.salaryForm)) return;
    if (!this.selectedId) return;
    this.loaderService.showLoader();
    this.editInfoService
      .editTalentSalary(
        {
          idCoin: this.salaryForm.get('currency')!.value,
          initialSalaryRxH: this.salaryForm.get('iAmountRxH')!.value ?? 0,
          finalSalaryRxH: this.salaryForm.get('fAmountRxH')!.value ?? 0,
          initialSalaryPlanilla:
            this.salaryForm.get('iAmountPlanilla')!.value ?? 0,
          finalSalaryPlanilla:
            this.salaryForm.get('fAmountPlanilla')!.value ?? 0,
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
          this.loaderService.hideLoader();
        },
      });
  }

  public onSveRedSoc() {
    if (!this.onSaveForm(this.redSocForm)) return;
    if (!this.selectedId) return;
    this.loaderService.showLoader();
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
          this.loaderService.hideLoader();
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
    this.resetEditProfilePicture();
    this.editProfilePicture = true;
  }

  public opEditSocMediaDlg() {
    const linkedin = this.customTalent?.linkedin ?? '';
    const github = this.customTalent?.github ?? '';
    this.redSocForm.reset({ linkedin, github });
    this.editSocialMediaDialog = true;
  }

  //Tocar esto
  public openEditSalaryDialog() {
    this.checkCurrencies();
    const currency = this.coin.id;
    const iAmountRxH = this.customTalent?.initialSalaryRxH ?? '';
    const fAmountRxH = this.customTalent?.finalSalaryRxH ?? '';
    const iAmountPlanilla = this.customTalent?.initialSalaryPlanilla ?? '';
    const fAmountPlanilla = this.customTalent?.finalSalaryPlanilla ?? '';
    this.salaryForm.reset({
      currency,
      iAmountRxH,
      fAmountRxH,
      iAmountPlanilla,
      fAmountPlanilla,
    });
    this.editSalaryDialog = true;
  }

  public hideEditSalaryDialog() {
    this.salaryForm.reset();
    this.editSalaryDialog = false;
  }

  public hideEditProfilePicture() {
    this.resetEditProfilePicture();
    this.editProfilePicture = false;
  }

  public hidEditSocMediaDlg() {
    this.redSocForm.reset();
    this.editSocialMediaDialog = false;
  }

  private resetEditProfilePicture() {
    this.profileForm.reset();
    this.photoText = 'Sube una nueva foto de perfil';
    this.photoDetailsText = 'PNG o JPG (max. 800x400px)';
    this.photoUploaded = false;
    this.base64photo = '';
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
    this.masterService.getGeneralData().subscribe({
      next: ({ currencies }) => {
        this.coins = currencies;
      },
    });
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

  //
  contactInfoDialog: boolean = false;
  phonePrefix!: string;

  ngOnChanges(): void {
    if (this.customTalent) {
      this.resetContactInfoForm();
      this.phonePrefix = this.customTalent.phone.split(' ')[0];
    }
  }

  public contactInfoForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    phone: ['', [Validators.required]],
  });

  openContactInfoDialog() {
    this.contactInfoDialog = true;
  }

  updateContactInfo() {
    if (!this.onSaveForm(this.contactInfoForm)) return;
    this.loaderService.showLoader();
    this.editInfoService
      .updateContactInfo({
        id: this.selectedId!,
        celular:
          this.customTalent?.phone.split(' ')[0] +
          ' ' +
          this.contactInfoForm.get('phone')!.value,
        email: this.contactInfoForm.get('email')!.value,
      })
      .subscribe({
        next: (resp) => {
          this.hideContactInfoDialog();
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
          this.loaderService.hideLoader();
        },
      });
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.contactInfoForm, field);
  }

  hideContactInfoDialog() {
    this.resetContactInfoForm();
    this.contactInfoDialog = false;
  }

  resetContactInfoForm() {
    const phone = this.customTalent!.phone.split(' ')[1];
    const email = this.customTalent!.email;
    this.contactInfoForm.reset({ email, phone });
  }

  copyEmail(input: HTMLInputElement) {
    input.select();
    document.execCommand('copy');
  }

  copyPhone(input: any) {
    input.inputViewChild.nativeElement.select();
    document.execCommand('copy');
  }
}
