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
    private confirmationService: ConfirmationService
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
    console.log(this.newLanguageForm.value);
    const { languages, proficiency, rating } = this.newLanguageForm.value;
    const body = {
      idiomaId: languages,
      nivelId: proficiency,
      nuEstrellas: rating,
    };
    this.addInfoService.addLang(body, this.selectedId).subscribe({
      next: (resp) => {
        console.log(resp.message);
        this.talentId.emit(Number(resp.id));
        this.hideNewLanguageDialog();
      },
    });
  }
  public onSveEditLangForm() {
    if (!this.onSaveForm(this.editLanguageForm)) return;
    if (!this.selectedId) return;
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
          this.hideEditLanguageDialog();
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
        },
      });
  }

  confirm() {
    this.confirmationService.confirm({
      header: 'Advertencia',
      message: 'Estás a punto de eliminar esta información. ¿Deseas continuar?',
      icon: 'pi pi-info-circle',

      accept: () => {
        //Actual logic to perform a confirmation
        this.hideEditLanguageDialog();
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
}
