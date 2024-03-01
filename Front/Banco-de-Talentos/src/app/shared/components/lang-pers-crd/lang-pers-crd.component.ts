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

  private get uploadLanguages(): LanguageModel[] {
    const cacheLanguages = this.masterService.cacheStorage.byLanguage.languages;
    return cacheLanguages;
  }

  public get uploadProficiency(): LangProficiencyModel[] {
    const cacheProficiencies =
      this.masterService.cacheStorage.byLangProficiency.proficiencies;
    return cacheProficiencies;
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.newLanguageForm, field);
  }

  onSveNewLanguageForm() {
    console.log(this.newLanguageForm.value);
  }

  OnLanguageChange() {
    this.newLanguageForm.controls['proficiency'].setValue(null);
  }

  isValidEditLangField(field: string) {
    return this.fValidator.isValidField(this.editLanguageForm, field);
  }

  onSveEditLangForm() {
    console.log(this.editLanguageForm.value);
  }

  OnEditLangChange() {
    this.editLanguageForm.controls['editProficiency'].setValue(null);
  }

  openEditLanguageDialog() {
    this.editLanguageDialog = true;
  }

  hideEditLanguageDialog() {
    this.editLanguageDialog = false;
  }

  openNewLanguageDialog() {
    this.newLanguageDialog = true;
  }

  hideNewLanguageDialog() {
    this.newLanguageDialog = false;
  }
}
