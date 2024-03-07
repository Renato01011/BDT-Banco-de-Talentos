import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { EducationalExperience } from '../../models/interfaces/talentResp.interfaces';

@Component({
  selector: 'shared-educ-pers-crd',
  templateUrl: './educ-pers-crd.component.html',
  styleUrls: ['./educ-pers-crd.component.scss'],
})
export class EducPersCrdComponent implements OnInit {
  @Input()
  public educExp: EducationalExperience[] = [];

  public newEducationalExperienceDialog: boolean = false;
  public editEducationalExperienceDialog: boolean = false;

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public newEducForm: FormGroup = this.fb.group({
    name: ['', [Validators.required]],
    career: ['', [Validators.required]],
    degree: ['', [Validators.required]],
    stDate: ['', [Validators.required]],
    edDate: ['', [Validators.required]],
    hFractal: [false],
    tPresent: [false],
  });

  public editEducForm: FormGroup = this.fb.group({
    editName: ['', [Validators.required]],
    editCareer: ['', [Validators.required]],
    editDegree: ['', [Validators.required]],
    editStDate: ['', [Validators.required]],
    editEdDate: ['', [Validators.required]],
    hFractal: [false],
    tPresent: [false],
  });

  ngOnInit(): void {}

  public onSveEditEducForm() {
    if (this.editEducForm.invalid) {
      this.editEducForm.markAllAsTouched();
      return;
    }
    console.log(this.editEducForm.value);
  }

  public onSveNewEducForm() {
    if (this.newEducForm.invalid) {
      this.newEducForm.markAllAsTouched();
      return;
    }
    console.log(this.newEducForm.value);
  }

  public isValidField(field: string) {
    return this.fValidator.isValidField(this.newEducForm, field);
  }

  public isValidEditEducField(field: string) {
    return this.fValidator.isValidField(this.editEducForm, field);
  }

  public openEditEducationalExperienceDialog(id: number) {
    const resp = this.findEducExpById(id);
    const editName = resp.institution;
    const editCareer = resp.major;
    const editDegree = resp.degree;
    const editStDate = new Date(resp.initialDate);
    const editEdDate = new Date(resp.finalDate);
    this.editEducForm.reset({
      editName,
      editCareer,
      editDegree,
      editStDate,
      editEdDate,
    });
    this.editEducationalExperienceDialog = true;
  }

  public hideEditEducationalExperienceDialog() {
    this.editEducForm.reset();
    this.editEducationalExperienceDialog = false;
  }

  public openNewEducationalExperienceDialog() {
    this.newEducationalExperienceDialog = true;
  }

  public hideNewEducationalExperienceDialog() {
    this.newEducForm.reset();
    this.newEducationalExperienceDialog = false;
  }

  private findEducExpById(id: number): EducationalExperience {
    const educExp = this.educExp.find(
      (exp) => exp.idEducationalExperience === id
    )!;
    return educExp;
  }

  public onCheckFractal() {
    if (this.newEducForm.get('hFractal')!.value) {
      this.newEducForm.controls['name'].disable();
      this.newEducForm.controls['name'].setValue('FRACTAL');
    } else {
      this.newEducForm.controls['name'].enable();
      this.newEducForm.controls['name'].setValue('');
    }
  }
  public onCheckCurrDate() {
    if (this.newEducForm.get('tPresent')!.value) {
      this.newEducForm.controls['edDate'].disable();
      this.newEducForm.controls['edDate'].setValue(new Date());
    } else {
      this.newEducForm.controls['edDate'].enable();
      this.newEducForm.controls['edDate'].setValue(null);
    }
  }

  public onCheckEdtFractal() {
    if (this.editEducForm.get('hFractal')!.value) {
      this.editEducForm.controls['editName'].disable();
      this.editEducForm.controls['editName'].setValue('FRACTAL');
    } else {
      this.editEducForm.controls['editName'].enable();
      this.editEducForm.controls['editName'].setValue('');
    }
  }
  public onCheckEdtCurrDate() {
    if (this.editEducForm.get('tPresent')!.value) {
      this.editEducForm.controls['editEdDate'].disable();
      this.editEducForm.controls['editEdDate'].setValue(new Date());
    } else {
      this.editEducForm.controls['editEdDate'].enable();
      this.editEducForm.controls['editEdDate'].setValue(null);
    }
  }
}
