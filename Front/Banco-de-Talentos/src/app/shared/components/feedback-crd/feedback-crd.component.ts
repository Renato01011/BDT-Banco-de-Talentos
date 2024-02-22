import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frm-val.service';

@Component({
  selector: 'shared-feedback-crd',
  templateUrl: './feedback-crd.component.html',
  styleUrls: ['./feedback-crd.component.scss'],
})
export class FeedbackCrdComponent implements OnInit {
  rating: number = 0;
  newFeedbackDialog: boolean = false;

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public feedBkForm: FormGroup = this.fb.group({
    feedback: ['', [Validators.required, Validators.minLength(20)]],
    rating: [0, [Validators.required]],
  });

  ngOnInit(): void {}

  isValidField(field: string) {
    return this.fValidator.isValidField(this.feedBkForm, field);
  }

  onSveFeedBkForm() {
    console.log(this.feedBkForm.value);
  }

  openNewFeedbackDialog() {
    this.newFeedbackDialog = true;
  }

  hideNewFeedbackDialog() {
    this.newFeedbackDialog = false;
  }
}
