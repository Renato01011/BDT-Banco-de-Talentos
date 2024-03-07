import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { WorkExperience } from '../../models/interfaces/talentResp.interfaces';
import { AddInfoService } from '../../service/addInfo/add-info.service';

@Component({
  selector: 'shared-exp-pers-crd',
  templateUrl: './exp-pers-crd.component.html',
  styleUrls: ['./exp-pers-crd.component.scss'],
})
export class ExpPersCrdComponent implements OnInit {
  @Input()
  public workExp: WorkExperience[] = [];
  @Input()
  public selectedId?: number;
  @Output()
  public talentId = new EventEmitter<number>();

  public newWorkExperienceDialog: boolean = false;
  public editWorkExperienceDialog: boolean = false;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private addInfoService: AddInfoService
  ) {}

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

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  public onSveNewExp() {
    if (!this.onSaveForm(this.newExpForm)) return;
    if (!this.selectedId) return;
    console.log(this.newExpForm.getRawValue());
    const { company, job, sDate, eDate } = this.newExpForm.getRawValue();
    const body = {
      empresa: company,
      puesto: job,
      fechaInicio: sDate,
      fechaFin: eDate,
    };
    this.addInfoService.addWorkExp(body, this.selectedId).subscribe({
      next: (resp) => {
        console.log(resp.message);
        this.talentId.emit(Number(resp.id));
        this.hideNewWorkExperienceDialog();
      },
    });
  }

  public onSveEditedExp() {
    if (!this.onSaveForm(this.editExpForm)) return;
    if (!this.selectedId) return;
    console.log(this.editExpForm.value);
  }

  public isValidField(field: string) {
    return this.fValidator.isValidField(this.newExpForm, field);
  }

  public isValidEditField(field: string) {
    return this.fValidator.isValidField(this.editExpForm, field);
  }

  public openEditWorkExperienceDialog(id: number) {
    const resp = this.findWorkExpById(id);
    const editCompany = resp.firm;
    const editJob = resp.jobTitle;
    const editDate = new Date(resp.intialDate);
    const editEndDate = new Date(resp.finalDate);
    this.editExpForm.reset({ editCompany, editJob, editDate, editEndDate });
    this.editWorkExperienceDialog = true;
  }

  public hideEditWorkExperienceDialog() {
    this.editExpForm.reset();
    this.editWorkExperienceDialog = false;
  }

  public openNewWorkExperienceDialog() {
    this.newWorkExperienceDialog = true;
  }

  public hideNewWorkExperienceDialog() {
    this.newExpForm.reset();
    this.newWorkExperienceDialog = false;
  }

  public expYear(initialDate: string, finalDate: string): number {
    const startDate = new Date(initialDate);
    const endDate = new Date(finalDate);
    const difference = endDate.getTime() - startDate.getTime();
    const millisecondsInYear = 1000 * 60 * 60 * 24 * 365.25;
    const years = difference / millisecondsInYear;
    return Math.floor(years);
  }

  private findWorkExpById(id: number): WorkExperience {
    const workExp = this.workExp.find((exp) => exp.idWorkExperience === id)!;
    return workExp;
  }

  public onCheckFractal() {
    if (this.newExpForm.get('hFractal')!.value) {
      this.newExpForm.controls['company'].disable();
      this.newExpForm.controls['company'].setValue('FRACTAL');
    } else {
      this.newExpForm.controls['company'].enable();
      this.newExpForm.controls['company'].setValue('');
    }
  }
  public onCheckCurrDate() {
    if (this.newExpForm.get('tPresent')!.value) {
      this.newExpForm.controls['eDate'].disable();
      this.newExpForm.controls['eDate'].setValue(new Date());
    } else {
      this.newExpForm.controls['eDate'].enable();
      this.newExpForm.controls['eDate'].setValue(null);
    }
  }

  public onEdtCheckFractal() {
    if (this.editExpForm.get('hFractal')!.value) {
      this.editExpForm.controls['editCompany'].disable();
      this.editExpForm.controls['editCompany'].setValue('FRACTAL');
    } else {
      this.editExpForm.controls['editCompany'].enable();
      this.editExpForm.controls['editCompany'].setValue('');
    }
  }
  public onEdtCheckCurrDate() {
    if (this.editExpForm.get('tPresent')!.value) {
      this.editExpForm.controls['editEndDate'].disable();
      this.editExpForm.controls['editEndDate'].setValue(new Date());
    } else {
      this.editExpForm.controls['editEndDate'].enable();
      this.editExpForm.controls['editEndDate'].setValue(null);
    }
  }
}
