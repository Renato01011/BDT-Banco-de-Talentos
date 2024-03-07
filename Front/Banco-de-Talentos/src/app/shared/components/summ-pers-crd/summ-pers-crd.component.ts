import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { EditInfoService } from '../../service/editInfo/edit-info.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'shared-summ-pers-crd',
  templateUrl: './summ-pers-crd.component.html',
  styleUrls: ['./summ-pers-crd.component.scss'],
})
export class SummPersCrdComponent implements OnInit {
  @Input()
  public description: string = '';
  @Input()
  public selectedId?: number;

  @Output()
  public talentId = new EventEmitter<number>();

  editDescriptionDialog: boolean = false;

  constructor(private fb: FormBuilder, private fValidator: FrmValService, private editInfoService: EditInfoService, private toastService: ToastService) {}

  public summForm: FormGroup = this.fb.group({
    description: [
      '',
      [Validators.required, Validators.minLength(10), Validators.maxLength(20)],
    ],
  });

  ngOnInit(): void {}

  isValidField(field: string) {
    return this.fValidator.isValidField(this.summForm, field);
  }

  public getErrDescField(field: string): string {
    let msg =
      this.fValidator.isRequiredErr(this.summForm, field) ??
      this.fValidator.isMinLengthErr(this.summForm, field) ??
      this.fValidator.isMaxLengthErr(this.summForm, field) ??
      'Este campo no puede estar nulo.';

    return msg;
  }

  onSveSummForm() {
    if (this.summForm.invalid) {
      this.summForm.markAllAsTouched();
      return;
    }
    if (!this.selectedId) return;
    this.editInfoService.editTalentDescription({
      description: this.summForm.get('description')!.value
    }, this.selectedId).subscribe({
      next: (resp) => {
        this.hideEditDescriptionDialog();
        this.toastService.addProperties(
          'success', 'Se editó correctamente', resp.message
        );
        this.talentId.emit(this.selectedId);
      }
    });
  }

  openEditDescriptionDialog() {
    const description = this.description;
    this.summForm.reset({ description });
    this.editDescriptionDialog = true;
  }

  hideEditDescriptionDialog() {
    this.summForm.reset();
    this.editDescriptionDialog = false;
  }
}
