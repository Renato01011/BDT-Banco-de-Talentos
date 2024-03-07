import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { AddInfoService } from '../../service/addInfo/add-info.service';
import {
  SoftSkill,
  TechnicalAbility,
} from '../../models/interfaces/talentResp.interfaces';

const yearExpRegEx = '^(?:\\d+(?:\\.(?:[0-9]|1[0-1]))?)$';
const justLettersRegEx = '^[a-zA-Z\\s]+$';

@Component({
  selector: 'shared-skills-pers-crd',
  templateUrl: './skills-pers-crd.component.html',
  styleUrls: ['./skills-pers-crd.component.scss'],
})
export class SkillsPersCrdComponent implements OnInit {
  @Input()
  softSkills: SoftSkill[] = [];
  @Input()
  techSkills: TechnicalAbility[] = [];
  @Input()
  public selectedId?: number;

  inlineTechnicalSkills: { name: string }[] = [];

  technicalSkillsDialog: boolean = false;
  softSkillsDialog: boolean = false;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private addInfoService: AddInfoService
  ) {}

  public techSkForm: FormGroup = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(10)]],
    yearExp: ['', [Validators.required, Validators.pattern(yearExpRegEx)]],
  });

  public softSkForm: FormGroup = this.fb.group({
    name: [
      '',
      [
        Validators.required,
        Validators.maxLength(10),
        Validators.pattern(justLettersRegEx),
      ],
    ],
  });

  ngOnInit(): void {}

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  public onSveTechSkForm() {
    if (!this.onSaveForm(this.techSkForm)) return;
    if (!this.selectedId) return;
    const { name, yearExp } = this.techSkForm.value;
    console.log(this.techSkForm.value);
    const body = {
      nombre: name,
      anios: yearExp,
    };
    this.addInfoService.addTechSkill(body, this.selectedId);
  }

  public onSveSoftSkForm() {
    if (!this.onSaveForm(this.softSkForm)) return;
    if (!this.selectedId) return;
    console.log(this.softSkForm.value);
  }

  public getErrYearExpField(field: string): string {
    return (
      this.fValidator.isRequiredErr(this.techSkForm, field) ??
      'La parte decimal debe estar entre 0 y 11.'
    );
  }

  public getErrTechNameExpField(field: string): string {
    return (
      this.fValidator.isRequiredErr(this.techSkForm, field) ??
      this.fValidator.isMaxLengthErr(this.techSkForm, field) ??
      'Este campo no puede estar nulo.'
    );
  }

  public getErrNameField(field: string): string {
    return (
      this.fValidator.isRequiredErr(this.softSkForm, field) ??
      this.fValidator.isMaxLengthErr(this.softSkForm, field) ??
      'Los números y otros caracteres no son válidos.'
    );
  }

  public isValidTechField(field: string) {
    return this.fValidator.isValidField(this.techSkForm, field);
  }

  public isValidSoftField(field: string) {
    return this.fValidator.isValidField(this.softSkForm, field);
  }

  public openNewTechnicalSkillDialog() {
    this.technicalSkillsDialog = true;
  }

  public hideNewTechnicalSkillDialog() {
    this.techSkForm.reset();
    this.technicalSkillsDialog = false;
  }
  public openNewSoftSkillDialog() {
    this.softSkillsDialog = true;
  }

  public hideNewSoftSkillDialog() {
    this.softSkForm.reset();
    this.softSkillsDialog = false;
  }

  public get inLineTechSkills(): { name: string }[] {
    const newArray = this.techSkills.map((skill) => ({
      name: `${skill.name} - ${skill.years}`,
    }));
    return newArray;
  }
}
