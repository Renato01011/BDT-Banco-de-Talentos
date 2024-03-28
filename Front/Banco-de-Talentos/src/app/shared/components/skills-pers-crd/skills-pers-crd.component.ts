import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { AddInfoService } from '../../service/addInfo/add-info.service';
import {
  SoftSkill,
  TechnicalAbility,
} from '../../models/interfaces/talentResp.interfaces';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { MasterService } from 'src/app/core/services/master/master.service';

const yearExpRegEx = '^(?:\\d+(?:\\.(?:[0-9]|1[0-1]))?)$';
const justLettersRegEx = '^[a-zA-ZÁáÉéÍíÓóÚúÜü\\s]+$';

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

  @Output()
  public talentId = new EventEmitter<number>();

  public isRecruiter: boolean = false;

  inlineTechnicalSkills: { name: string }[] = [];

  technicalSkillsDialog: boolean = false;
  softSkillsDialog: boolean = false;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private addInfoService: AddInfoService,
    private toastService: ToastService,
    private masterService: MasterService,
    private loaderService: LoaderService,
    private authService: AuthService
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
        Validators.maxLength(20),
        Validators.pattern(justLettersRegEx),
      ],
    ],
  });

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

  public onSveTechSkForm() {
    if (!this.onSaveForm(this.techSkForm)) return;
    if (!this.selectedId) return;
    const { name, yearExp } = this.techSkForm.value;
    const body = {
      nombre: name,
      anios: yearExp,
    };
    this.loaderService.showLoader();
    this.addInfoService.addTechSkill(body, this.selectedId).subscribe({
      next: (resp) => {
        this.toastService.addProperties(
          'success',
          'Se agregó correctamente',
          resp.message
        );
        this.talentId.emit(Number(resp.id));
        this.loaderService.hideLoader();
        this.masterService.cacheStorage.byTechSkill.techSkills = [];
        this.hideNewTechnicalSkillDialog();
      },
    });
  }

  public onSveSoftSkForm() {
    if (!this.onSaveForm(this.softSkForm)) return;
    if (!this.selectedId) return;

    const { name } = this.softSkForm.value;
    const body = {
      nombre: name,
    };
    this.loaderService.showLoader();
    this.addInfoService.addSoftSkill(body, this.selectedId).subscribe({
      next: (resp) => {
        this.toastService.addProperties(
          'success',
          'Se agregó correctamente',
          resp.message
        );
        this.talentId.emit(Number(resp.id));
        this.loaderService.hideLoader();
        this.hideNewSoftSkillDialog();
      },
    });
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
      'Los números no son válidos.'
    );
  }

  public isValidTechField(field: string) {
    return this.fValidator.isValidField(this.techSkForm, field);
  }

  public isValidSoftField(field: string) {
    return this.fValidator.isValidField(this.softSkForm, field);
  }

  public openNewTechnicalSkillDialog() {
    this.techSkForm.reset();
    this.technicalSkillsDialog = true;
  }

  public hideNewTechnicalSkillDialog() {
    this.techSkForm.reset();
    this.technicalSkillsDialog = false;
  }
  public openNewSoftSkillDialog() {
    this.softSkForm.reset()
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
