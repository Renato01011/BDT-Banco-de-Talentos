import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { EducationalExperience } from '../../models/interfaces/talentResp.interfaces';
import { AddInfoService } from '../../service/addInfo/add-info.service';
import { EditInfoService } from '../../service/editInfo/edit-info.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'shared-educ-pers-crd',
  templateUrl: './educ-pers-crd.component.html',
  styleUrls: ['./educ-pers-crd.component.scss'],
})
export class EducPersCrdComponent implements OnInit {
  @Input()
  public educExp: EducationalExperience[] = [];
  @Input()
  public selectedId?: number;
  @Output()
  public talentId = new EventEmitter<number>();

  public newEducationalExperienceDialog: boolean = false;
  public editEducationalExperienceDialog: boolean = false;

  currEditingEducExp: number = 0;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private addInfoService: AddInfoService,
    private editInfoService: EditInfoService,
    private toastService: ToastService,
    private confirmationService: ConfirmationService
  ) {}

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

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  public onSveEditEducForm() {
    if (!this.onSaveForm(this.editEducForm)) return;
    if (!this.selectedId) return;
    this.editInfoService
      .editEducationalExperience(
        {
          institucion: this.editEducForm.get('editName')!.value,
          carrera: this.editEducForm.get('editCareer')!.value,
          grado: this.editEducForm.get('editDegree')!.value,
          fechaInicio: this.editEducForm.get('editStDate')!.value,
          fechaFin: this.editEducForm.get('editEdDate')!.value,
        },
        this.selectedId,
        this.currEditingEducExp
      )
      .subscribe({
        next: (resp) => {
          this.hideEditEducationalExperienceDialog();
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
        },
      });
  }

  public onSveNewEducForm() {
    if (!this.onSaveForm(this.newEducForm)) return;
    if (!this.selectedId) return;
    console.log(this.newEducForm.getRawValue());
    const { name, career, degree, stDate, edDate } =
      this.newEducForm.getRawValue();
    const body = {
      institucion: name,
      carrera: career,
      grado: degree,
      fechaInicio: stDate,
      fechaFin: edDate,
    };
    this.addInfoService.addEducExp(body, this.selectedId).subscribe({
      next: (resp) => {
        console.log(resp.message);
        this.talentId.emit(Number(resp.id));
        this.hideNewEducationalExperienceDialog();
      },
    });
  }

  confirm() {
    this.confirmationService.confirm({
      header: 'Advertencia',
      message: 'Estás a punto de eliminar esta información. ¿Deseas continuar?',
      icon: 'pi pi-info-circle',

      accept: () => {
        //Actual logic to perform a confirmation
        this.hideEditEducationalExperienceDialog();
      },
    });
  }

  public isValidField(field: string) {
    return this.fValidator.isValidField(this.newEducForm, field);
  }

  public isValidEditEducField(field: string) {
    return this.fValidator.isValidField(this.editEducForm, field);
  }

  public openEditEducationalExperienceDialog(id: number) {
    this.currEditingEducExp = id;
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
    this.currEditingEducExp = 0;
    this.editEducForm.reset();
    this.onCheckEdtFractal();
    this.onCheckEdtCurrDate();
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
