import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';

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

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

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
    console.log(this.summForm.value);
    // this.talentId.emit(Number(resp.id));
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
