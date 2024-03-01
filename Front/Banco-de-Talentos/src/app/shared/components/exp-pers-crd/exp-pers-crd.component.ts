import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';

@Component({
  selector: 'shared-exp-pers-crd',
  templateUrl: './exp-pers-crd.component.html',
  styleUrls: ['./exp-pers-crd.component.scss'],
})
export class ExpPersCrdComponent implements OnInit {
  newWorkExperienceDialog: boolean = false;
  editWorkExperienceDialog: boolean = false;

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public newExpForm: FormGroup = this.fb.group({
    company: ['', [Validators.required]],
    job: ['', [Validators.required]],
    sDate: ['', [Validators.required]],
    eDate: ['', [Validators.required]],
    hFractal: [false],
    tPresent: [false],
  });

  public editExpForm: FormGroup = this.fb.group({
    editCompany: ['', [Validators.required]],
    editJob: ['', [Validators.required]],
    editDate: ['', [Validators.required]],
    editEndDate: ['', [Validators.required]],
    hFractal: [false],
    tPresent: [false],
  });

  ngOnInit(): void {}

  isValidField(field: string) {
    return this.fValidator.isValidField(this.newExpForm, field);
  }

  onCheckFractal() {
    if (this.newExpForm.get('hFractal')!.value) {
      this.newExpForm.controls['company'].disable();
      this.newExpForm.controls['company'].setValue('FRACTAL');
    } else {
      this.newExpForm.controls['company'].enable();
      this.newExpForm.controls['company'].setValue('');
    }
  }
  onCheckCurrDate() {
    if (this.newExpForm.get('tPresent')!.value) {
      this.newExpForm.controls['eDate'].disable();
      this.newExpForm.controls['eDate'].setValue(new Date());
    } else {
      this.newExpForm.controls['eDate'].enable();
      this.newExpForm.controls['eDate'].setValue(null);
    }
  }

  isValidEditField(field: string) {
    return this.fValidator.isValidField(this.editExpForm, field);
  }

  onEdtCheckFractal() {
    if (this.editExpForm.get('hFractal')!.value) {
      this.editExpForm.controls['editCompany'].disable();
      this.editExpForm.controls['editCompany'].setValue('FRACTAL');
    } else {
      this.editExpForm.controls['editCompany'].enable();
      this.editExpForm.controls['editCompany'].setValue('');
    }
  }
  onEdtCheckCurrDate() {
    if (this.editExpForm.get('tPresent')!.value) {
      this.editExpForm.controls['editEndDate'].disable();
      this.editExpForm.controls['editEndDate'].setValue(new Date());
    } else {
      this.editExpForm.controls['editEndDate'].enable();
      this.editExpForm.controls['editEndDate'].setValue(null);
    }
  }

  onSveNewExp() {
    console.log(this.newExpForm.value);
  }

  onSveEditedExp() {
    console.log(this.newExpForm.value);
  }

  openEditWorkExperienceDialog() {
    this.editWorkExperienceDialog = true;
  }

  hideEditWorkExperienceDialog() {
    this.editExpForm.reset();
    this.editWorkExperienceDialog = false;
  }

  openNewWorkExperienceDialog() {
    this.newWorkExperienceDialog = true;
  }

  hideNewWorkExperienceDialog() {
    this.newExpForm.reset();
    this.newWorkExperienceDialog = false;
  }
}
