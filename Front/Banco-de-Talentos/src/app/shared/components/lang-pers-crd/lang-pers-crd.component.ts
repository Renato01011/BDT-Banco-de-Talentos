import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { MasterService } from 'src/app/core/services/master/master.service';
import {
  LangProficiencyModel,
  LanguageModel,
} from '../../models/interfaces/master.interfaces';
import { LanguageLevel } from '../../models/interfaces/talentResp.interfaces';
import { AddInfoService } from '../../service/addInfo/add-info.service';
import { EditInfoService } from '../../service/editInfo/edit-info.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { ConfirmationService } from 'primeng/api';
import { DeleteInfoService } from '../../service/deleteInfo/delete-info.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { LoaderService } from 'src/app/core/services/loader/loader.service';

@Component({
  selector: 'shared-lang-pers-crd',
  templateUrl: './lang-pers-crd.component.html',
  styleUrls: ['./lang-pers-crd.component.scss'],
})
export class LangPersCrdComponent implements OnInit {
  @Input()
  public langProficiency: LanguageLevel[] = [];
  @Input()
  public selectedId?: number;
  @Output()
  public talentId = new EventEmitter<number>();

  public isRecruiter: boolean = false;

  newLanguageDialog: boolean = false;
  editLanguageDialog: boolean = false;

  currEditingLangProf: number = 0;

  language: LanguageModel[] = [];

  proficiency: LangProficiencyModel[] = [];

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private masterService: MasterService,
    private addInfoService: AddInfoService,
    private editInfoService: EditInfoService,
    private toastService: ToastService,
    private confirmationService: ConfirmationService,
    private deleteInfoService: DeleteInfoService,
    private loaderService: LoaderService,
    private authService: AuthService
  ) {}

  public newLanguageForm: FormGroup = this.fb.group({
    languages: ['', [Validators.required]],
    proficiency: ['', [Validators.required]],
    rating: ['', [Validators.required]],
  });

  public editLanguageForm: FormGroup = this.fb.group({
    editLanguages: ['', [Validators.required]],
    editProficiency: ['', [Validators.required]],
    editRating: ['', [Validators.required]],
  });

  ngOnInit(): void {
    this.language = this.uploadLanguages;
    this.proficiency = this.uploadProficiency;
    this.isRecruiter = this.authService.isRecruiter;
  }

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  public onSveNewLanguageForm() {
    if (!this.onSaveForm(this.newLanguageForm)) return;
    if (!this.selectedId) return;

    const { languages, proficiency, rating } = this.newLanguageForm.value;
    const body = {
      idiomaId: languages,
      nivelId: proficiency,
      nuEstrellas: rating,
    };
    this.loaderService.showLoader();
    this.addInfoService.addLang(body, this.selectedId).subscribe({
      next: (resp) => {
        this.toastService.addProperties(
          'success',
          'Se agregó correctamente',
          resp.message
        );
        this.talentId.emit(Number(resp.id));
        this.loaderService.hideLoader();
        this.hideNewLanguageDialog();
      },
    });
  }
  public onSveEditLangForm() {
    if (!this.onSaveForm(this.editLanguageForm)) return;
    if (!this.selectedId) return;
    this.loaderService.showLoader();
    this.editInfoService
      .editLanguageExpertise(
        {
          idiomaId: this.editLanguageForm.get('editLanguages')!.value,
          nivelId: this.editLanguageForm.get('editProficiency')!.value,
          nuEstrellas: this.editLanguageForm.get('editRating')!.value,
        },
        this.selectedId,
        this.currEditingLangProf
      )
      .subscribe({
        next: (resp) => {
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
          this.loaderService.hideLoader();
          this.hideEditLanguageDialog();
        },
      });
  }

  confirm() {
    this.confirmationService.confirm({
      header: 'Advertencia',
      message: 'Estás a punto de eliminar esta información. ¿Deseas continuar?',
      icon: 'pi pi-info-circle',

      accept: () => {
        if (!this.selectedId) return;
        this.loaderService.showLoader();
        this.deleteInfoService
          .deleteLanguageExpertise(this.selectedId, this.currEditingLangProf)
          .subscribe({
            next: (resp) => {
              this.toastService.addProperties(
                'success',
                'Se eliminó correctamente',
                resp.message
              );
              this.talentId.emit(this.selectedId);
              this.loaderService.hideLoader();
              this.hideEditLanguageDialog();
            },
          });
      },
    });
  }

  public isValidField(field: string) {
    return this.fValidator.isValidField(this.newLanguageForm, field);
  }

  public isValidEditLangField(field: string) {
    return this.fValidator.isValidField(this.editLanguageForm, field);
  }

  public OnLanguageChange() {
    this.newLanguageForm.controls['proficiency'].setValue(null);
  }

  public OnEditLangChange() {
    this.editLanguageForm.controls['editProficiency'].setValue(null);
  }

  public openEditLanguageDialog(id: number) {
    this.checkLanguages();
    this.checkProficiency();
    const resp = this.findLangById(id);
    this.currEditingLangProf = id;
    const editLanguages = resp.idLanguage;
    const editProficiency = resp.idProficiency;
    const editRating = resp.starCount;
    this.editLanguageForm.reset({
      editLanguages,
      editProficiency,
      editRating,
    });
    this.editLanguageDialog = true;
  }

  public hideEditLanguageDialog() {
    this.currEditingLangProf = 0;
    this.editLanguageDialog = false;
  }

  public openNewLanguageDialog() {
    this.newLanguageForm.reset();
    this.checkLanguages();
    this.checkProficiency();
    this.newLanguageDialog = true;
  }

  public hideNewLanguageDialog() {
    this.newLanguageDialog = false;
  }

  private findLangById(id: number): LanguageLevel {
    const lang = this.langProficiency.find(
      (lang) => lang.idTalentLanguage === id
    )!;
    return lang;
  }

  private get uploadLanguages(): LanguageModel[] {
    const cacheLanguages = this.masterService.cacheStorage.byLanguage.languages;
    return cacheLanguages;
  }

  public get uploadProficiency(): LangProficiencyModel[] {
    const cacheProficiencies =
      this.masterService.cacheStorage.byLangProficiency.proficiencies;
    return cacheProficiencies;
  }

  private get isCacheLanguagesEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byLanguage.languages ||
      this.masterService.cacheStorage.byLanguage.languages.length === 0
    );
  }

  private checkLanguages() {
    if (this.isCacheLanguagesEmpty) {
      this.getLanguages();
    } else {
      const cacheLanguages =
        this.masterService.cacheStorage.byLanguage.languages;
      this.language = cacheLanguages;
    }
  }

  private getLanguages(): void {
    this.masterService.getLanguages().subscribe({
      next: (languages) => {
        this.language = languages;
      },
    });
  }

  private get isCacheProficiencyEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byLangProficiency.proficiencies ||
      this.masterService.cacheStorage.byLangProficiency.proficiencies.length ===
        0
    );
  }

  private checkProficiency() {
    if (this.isCacheProficiencyEmpty) {
      this.getProficiencies();
    } else {
      const cacheProficiencies =
        this.masterService.cacheStorage.byLangProficiency.proficiencies;
      this.proficiency = cacheProficiencies;
    }
  }

  private getProficiencies(): void {
    this.masterService.getLangProficiency().subscribe({
      next: (proficiency) => {
        this.proficiency = proficiency;
      },
    });
  }
}
