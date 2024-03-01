import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';

const yearExpRegEx = '^(?:[0-9]|1[0-9]|20)$';

interface Favorite {
  name: string;
  code: string;
}
interface Soft {
  name: string;
}
@Component({
  selector: 'shared-skills-pers-crd',
  templateUrl: './skills-pers-crd.component.html',
  styleUrls: ['./skills-pers-crd.component.scss'],
})
export class SkillsPersCrdComponent implements OnInit {
  technicalSkills: Favorite[] = [];
  inlineTechnicalSkills: any[] = [];
  softSkills: Soft[] = [];

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

  ngOnInit(): void {
    this.technicalSkills = [
      { name: 'Docker', code: '1.3' },
      { name: 'Express', code: '2' },
      { name: 'Github', code: '3.5' },
      { name: 'Data Structure', code: '4' },
      { name: 'SQL', code: '4' },
      { name: 'NoSQL', code: '4' },
      { name: 'Node.js', code: '5' },
    ];
    this.inlineTechnicalSkills = this.crearNuevoArray();
    this.softSkills = [
      { name: 'Trabajo en equipo' },
      { name: 'Empatía' },
      { name: 'Resolución de problemas' },
      { name: 'Adaptabilidad' },
    ];
  }

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

  crearNuevoArray(): { name: string }[] {
    const newArray = this.technicalSkills.map((skill) => ({
      name: `${skill.name} - ${skill.code}`,
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
