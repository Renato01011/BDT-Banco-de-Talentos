import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';

@Component({
  selector: 'shared-feedback-crd',
  templateUrl: './feedback-crd.component.html',
  styleUrls: ['./feedback-crd.component.scss'],
})
export class FeedbackCrdComponent implements OnInit {
  rating: number = 0;
  newFeedbackDialog: boolean = false;
  @Input()
  public selectedId?: number;

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public feedBkForm: FormGroup = this.fb.group({
    feedback: ['', [Validators.required, Validators.minLength(20)]],
    rating: [0, [Validators.required]],
  });

  ngOnInit(): void {}

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  onSveFeedBkForm() {
    if (!this.onSaveForm(this.feedBkForm)) return;
    if (!this.selectedId) return;
    console.log(this.feedBkForm.value);
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.feedBkForm, field);
  }

  openNewFeedbackDialog() {
    this.newFeedbackDialog = true;
  }

  hideNewFeedbackDialog() {
    this.newFeedbackDialog = false;
  }
}
