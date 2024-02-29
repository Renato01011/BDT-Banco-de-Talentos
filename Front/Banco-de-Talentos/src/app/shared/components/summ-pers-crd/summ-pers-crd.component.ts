import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';

@Component({
  selector: 'shared-summ-pers-crd',
  templateUrl: './summ-pers-crd.component.html',
  styleUrls: ['./summ-pers-crd.component.scss'],
})
export class SummPersCrdComponent implements OnInit {
  editDescriptionDialog: boolean = false;

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public summForm: FormGroup = this.fb.group({
    description: ['', [Validators.required, Validators.minLength(100)]],
  });

  ngOnInit(): void {}

  isValidField(field: string) {
    return this.fValidator.isValidField(this.summForm, field);
  }

  onSveSummForm() {
    console.log(this.summForm.value);
  }

  openEditDescriptionDialog() {
    this.editDescriptionDialog = true;
  }

  hideEditDescriptionDialog() {
    this.editDescriptionDialog = false;
  }
}
