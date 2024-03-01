import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import {
  SoftSkill,
  TechnicalAbility,
} from '../../models/interfaces/talentResp.interfaces';

const yearExpRegEx = '^(?:[0-9]|1[0-9]|20)$';

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

  inlineTechnicalSkills: { name: string }[] = [];

  technicalSkillsDialog: boolean = false;
  softSkillsDialog: boolean = false;

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public techSkForm: FormGroup = this.fb.group({
    name: ['', [Validators.required]],
    yearExp: ['', [Validators.required, Validators.pattern(yearExpRegEx)]],
  });

  public softSkForm: FormGroup = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(4)]],
  });

  ngOnInit(): void {}

  onSveTechSkForm() {
    console.log(this.techSkForm.value);
  }

  onSveSoftSkForm() {
    console.log(this.techSkForm.value);
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.techSkForm, field);
  }

  isValidSoftField(field: string) {
    return this.fValidator.isValidField(this.softSkForm, field);
  }

  public get inLineTechSkills(): { name: string }[] {
    const newArray = this.techSkills.map((skill) => ({
      name: `${skill.name} - ${skill.years}`,
    }));
    return newArray;
  }

  openNewTechnicalSkillDialog() {
    this.technicalSkillsDialog = true;
  }

  hideNewTechnicalSkillDialog() {
    this.technicalSkillsDialog = false;
  }
  openNewSoftSkillDialog() {
    this.softSkillsDialog = true;
  }

  hideNewSoftSkillDialog() {
    this.softSkillsDialog = false;
  }
}
