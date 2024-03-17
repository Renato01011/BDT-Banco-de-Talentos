import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { WorkExperience } from '../../models/interfaces/talentResp.interfaces';
import { AddInfoService } from '../../service/addInfo/add-info.service';
import { EditInfoService } from '../../service/editInfo/edit-info.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { ConfirmationService } from 'primeng/api';
import { DeleteInfoService } from '../../service/deleteInfo/delete-info.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';

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

  public isRecruiter: boolean = false;

  public newWorkExperienceDialog: boolean = false;
  public editWorkExperienceDialog: boolean = false;

  public maxDate: Date = new Date();

  currEditingWorkExp: number = 0;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private addInfoService: AddInfoService,
    private editInfoService: EditInfoService,
    private toastService: ToastService,
    private confirmationService: ConfirmationService,
    private deleteInfoService: DeleteInfoService,
    private authService: AuthService
  ) {}

  public newExpForm: FormGroup = this.fb.group(
    {
      company: ['', [Validators.required, Validators.maxLength(50)]],
      job: ['', [Validators.required, Validators.maxLength(40)]],
      sDate: ['', [Validators.required]],
      eDate: ['', [Validators.required]],
      hFractal: [false],
      tPresent: [false],
    },
    {
      validators: [this.fValidator.compareDates('sDate', 'eDate')],
    }
  );

  public editExpForm: FormGroup = this.fb.group(
    {
      editCompany: ['', [Validators.required, Validators.maxLength(50)]],
      editJob: ['', [Validators.required, Validators.maxLength(40)]],
      editDate: ['', [Validators.required]],
      editEndDate: ['', [Validators.required]],
      hFractal: [false],
      tPresent: [false],
    },
    {
      validators: [this.fValidator.compareDates('editDate', 'editEndDate')],
    }
  );

  ngOnInit(): void {
    this.isRecruiter = this.authService.isRecruiter;
  }

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
        //console.log(resp.message);
        this.toastService.addProperties(
          'success',
          'Se agregó correctamente',
          resp.message
        );
        this.talentId.emit(Number(resp.id));
        this.hideNewWorkExperienceDialog();
      },
    });
  }

  public onSveEditedExp() {
    if (!this.onSaveForm(this.editExpForm)) return;
    if (!this.selectedId) return;
    this.editInfoService
      .editWorkExperience(
        {
          empresa: this.editExpForm.get('editCompany')!.value,
          puesto: this.editExpForm.get('editJob')!.value,
          fechaInicio: this.editExpForm.get('editDate')!.value,
          fechaFin: this.editExpForm.get('editEndDate')!.value,
        },
        this.selectedId,
        this.currEditingWorkExp
      )
      .subscribe({
        next: (resp) => {
          this.hideEditWorkExperienceDialog();
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
        if (!this.selectedId) return;
        this.deleteInfoService
          .deleteWorkExperience(this.selectedId, this.currEditingWorkExp)
          .subscribe({
            next: (resp) => {
              this.hideEditWorkExperienceDialog();
              this.toastService.addProperties(
                'success',
                'Se eliminó correctamente',
                resp.message
              );
              this.talentId.emit(this.selectedId);
            },
          });
      },
    });
  }

  public isValidField(field: string) {
    return this.fValidator.isValidField(this.newExpForm, field);
  }

  public isValidEditField(field: string) {
    return this.fValidator.isValidField(this.editExpForm, field);
  }

  public getErrNewCompanyJobField(field: string): string {
    let msg =
      this.fValidator.isRequiredErr(this.newExpForm, field) ??
      this.fValidator.isMaxLengthErr(this.newExpForm, field) ??
      'Este campo no debe ser nulo.';
    return msg;
  }

  public getErrEditCompanyJobField(field: string): string {
    let msg =
      this.fValidator.isRequiredErr(this.editExpForm, field) ??
      this.fValidator.isMaxLengthErr(this.editExpForm, field) ??
      'Este campo no debe ser nulo.';
    return msg;
  }

  public getErrEndDateField(field: string): string {
    let msg =
      this.fValidator.isRequiredErr(this.newExpForm, field) ??
      ' La fecha de finalización debe ser posterior a la fecha de inicio.';
    return msg;
  }

  public getErrEditEndDateField(field: string): string {
    let msg =
      this.fValidator.isRequiredErr(this.editExpForm, field) ??
      ' La fecha de finalización debe ser posterior a la fecha de inicio.';
    return msg;
  }

  public openEditWorkExperienceDialog(id: number) {
    this.currEditingWorkExp = id;
    const resp = this.findWorkExpById(id);
    const editCompany = resp.firm;
    const editJob = resp.jobTitle;
    const editDate = new Date(resp.intialDate);
    const editEndDate = new Date(resp.finalDate);
    this.editExpForm.reset({ editCompany, editJob, editDate, editEndDate });
    this.editWorkExperienceDialog = true;
  }

  public hideEditWorkExperienceDialog() {
    this.currEditingWorkExp = 0;
    this.editExpForm.reset();
    this.onEdtCheckFractal();
    this.onEdtCheckCurrDate();
    this.editWorkExperienceDialog = false;
  }

  public openNewWorkExperienceDialog() {
    this.newWorkExperienceDialog = true;
  }

  public hideNewWorkExperienceDialog() {
    this.onCheckFractal();
    this.onCheckCurrDate();
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
