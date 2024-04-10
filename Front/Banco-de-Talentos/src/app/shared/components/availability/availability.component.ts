import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { EditInfoService } from '../../service/editInfo/edit-info.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { LoaderService } from 'src/app/core/services/loader/loader.service';

@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.scss']
})
export class AvailabilityComponent implements OnInit {

  @Input()
  public availability: string = '';
  @Input()
  public selectedId?: number;

  @Output()
  public talentId = new EventEmitter<number>();
  editAvailabilityDialog: boolean = false;
  public isRecruiter: boolean = false;

  constructor(private fb: FormBuilder,
    private fValidator: FrmValService,
    private editInfoService: EditInfoService,
    private toastService: ToastService,
    private loaderService: LoaderService,
    private authService: AuthService) { }

  public availabilityForm: FormGroup = this.fb.group({
    availability: [
      '',
      [
        Validators.required,
        Validators.maxLength(50),
      ],
    ],
  });

  ngOnInit(): void {
    this.isRecruiter = this.authService.isRecruiter;
  }

  isValidField(field: string) {
    return this.fValidator.isValidField(this.availabilityForm, field);
  }

  public getErrDescField(field: string): string {
    let msg =
      this.fValidator.isRequiredErr(this.availabilityForm, field) ??
      this.fValidator.isMaxLengthErr(this.availabilityForm, field) ??
      'Este campo no debe ser nulo.';

    return msg;
  }

  onSaveAvailability() {
    if (this.availabilityForm.invalid) {
      this.availabilityForm.markAllAsTouched();
      return;
    }

    if (!this.selectedId) return;
    this.loaderService.showLoader();
    this.editInfoService.editTalentAvailability(
      { availability: this.availabilityForm.get('availability')!.value },
      this.selectedId
    ).subscribe(
      {
        next: (resp) => {
          this.hideEditAvailabilityDialog();
          this.toastService.addProperties(
            'success',
            'Se editó correctamente',
            resp.message
          );
          this.talentId.emit(this.selectedId);
          this.loaderService.hideLoader();
        },
      }
    )
  }

  openEditAvailabilityDialog() {
    const availability = this.availability;
    this.availabilityForm.reset({ availability });
    this.editAvailabilityDialog = true;
  }

  hideEditAvailabilityDialog() {
    this.availabilityForm.reset();
    this.editAvailabilityDialog = false;
  }
}
