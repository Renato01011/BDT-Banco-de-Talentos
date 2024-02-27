import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frm-val.service';

@Component({
  selector: 'shared-lang-pers-crd',
  templateUrl: './lang-pers-crd.component.html',
  styleUrls: ['./lang-pers-crd.component.scss'],
})
export class LangPersCrdComponent implements OnInit {
  rating: number = 0;

  newLanguageDialog: boolean = false;
  editLanguageDialog: boolean = false;

  languages: any[] = [];
  levels: any[] = [];

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

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
    this.languages = [
      { name: 'Ingles', code: 'in' },
      { name: 'Español', code: 'es' },
    ];
    this.levels = [
      { name: 'Básico', code: '1' },
      { name: 'Intermedio', code: '2' },
      { name: 'Avanzado', code: '3' },
      { name: 'Nativo', code: '4' },
    ];
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
