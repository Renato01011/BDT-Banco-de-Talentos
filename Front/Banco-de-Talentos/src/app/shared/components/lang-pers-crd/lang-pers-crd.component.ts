import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { MasterService } from 'src/app/core/services/master/master.service';
import {
  LangProficiencyModel,
  LanguageModel,
} from '../../models/interfaces/master.interfaces';
import { LanguageLevel } from '../../models/interfaces/talentResp.interfaces';

@Component({
  selector: 'shared-lang-pers-crd',
  templateUrl: './lang-pers-crd.component.html',
  styleUrls: ['./lang-pers-crd.component.scss'],
})
export class LangPersCrdComponent implements OnInit {
  @Input()
  langProficiency: LanguageLevel[] = [];
  @Input()
  public selectedId?: number;

  newLanguageDialog: boolean = false;
  editLanguageDialog: boolean = false;

  language: LanguageModel[] = [];

  proficiency: LangProficiencyModel[] = [];

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private masterService: MasterService
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
    console.log(this.newLanguageForm.value);
    if (!this.selectedId) return;
  }
  public onSveEditLangForm() {
    if (!this.onSaveForm(this.editLanguageForm)) return;
    console.log(this.editLanguageForm.value);
    if (!this.selectedId) return;
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
    const editLanguages = resp.languageName;
    const editProficiency = resp.proficiency;
    const editRating = resp.starCount;
    this.editLanguageForm.reset({
      editLanguages,
      editProficiency,
      editRating,
    });
    this.editLanguageDialog = true;
  }

  public hideEditLanguageDialog() {
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
