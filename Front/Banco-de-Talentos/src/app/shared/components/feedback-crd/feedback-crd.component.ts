import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';

@Component({
  selector: 'shared-feedback-crd',
  templateUrl: './feedback-crd.component.html',
  styleUrls: ['./feedback-crd.component.scss'],
})
export class FeedbackCrdComponent implements OnInit {
  @Input()
  public selectedId?: number;

  public rating: number = 0;
  public newFeedbackDialog: boolean = false;
  public idUser?: number;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private authService: AuthService
  ) {}

  public feedBkForm: FormGroup = this.fb.group({
    feedback: [
      '',
      [
        Validators.required,
        Validators.minLength(20),
        Validators.maxLength(100),
      ],
    ],
    rating: [0, [Validators.required]],
  });

  ngOnInit(): void {
    this.idUser = this.authService.idUser;
  }

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  onSveFeedBkForm() {
    if (!this.onSaveForm(this.feedBkForm || !this.selectedId)) return;
    console.log(this.feedBkForm.value);
    console.log(this.idUser);
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.feedBkForm, field);
  }

  public getErrFeedBkField(field: string): string {
    let msg =
      this.fValidator.isRequiredErr(this.feedBkForm, field) ??
      this.fValidator.isMinLengthErr(this.feedBkForm, field) ??
      this.fValidator.isMaxLengthErr(this.feedBkForm, field) ??
      'Este campo no debe ser nulo.';

    return msg;
  }

  openNewFeedbackDialog() {
    this.newFeedbackDialog = true;
  }

  hideNewFeedbackDialog() {
    this.feedBkForm.reset();
    this.newFeedbackDialog = false;
  }
}
