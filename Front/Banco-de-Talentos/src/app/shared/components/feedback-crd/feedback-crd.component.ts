import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { AddInfoService } from '../../service/addInfo/add-info.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';

@Component({
  selector: 'shared-feedback-crd',
  templateUrl: './feedback-crd.component.html',
  styleUrls: ['./feedback-crd.component.scss'],
})
export class FeedbackCrdComponent implements OnInit {
  @Input()
  public selectedId?: number;

  @Output()
  public talentId = new EventEmitter<number>();

  public rating: number = 0;
  public newFeedbackDialog: boolean = false;
  public idUser?: number;

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private authService: AuthService,
    private addInfoService: AddInfoService,
    private toastService: ToastService
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
    if (this.idUser == null || this.selectedId == null) return;
    //console.log(this.feedBkForm.value);
    //console.log(this.idUser);
    const { feedback, rating } = this.feedBkForm.getRawValue();
    const body = {
      nuEstrellas: rating,
      descripcion: feedback,
      userFromId: this.idUser,
    };
    this.addInfoService.addFeedback(body, this.selectedId).subscribe({
      next: (resp) => {
        this.toastService.addProperties(
          'success',
          'Se agregó correctamente',
          resp.message
        );
        this.talentId.emit(Number(resp.id));
        this.hideNewFeedbackDialog();
      }
    });
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
