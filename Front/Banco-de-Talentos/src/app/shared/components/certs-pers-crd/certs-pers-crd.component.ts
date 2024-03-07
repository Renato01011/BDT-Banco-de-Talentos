import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Document } from '../../models/interfaces/talentResp.interfaces';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';

@Component({
  selector: 'shared-certs-pers-crd',
  templateUrl: './certs-pers-crd.component.html',
  styleUrls: ['./certs-pers-crd.component.scss'],
})
export class CertsPersCrdComponent implements OnInit {
  @Input()
  public documents: Document[] = [];
  @Input()
  public selectedId?: number;
  @Output()
  public talentId = new EventEmitter<number>();

  public addFileDialog: boolean = false;

  public responsiveOptions: any[] = [];

  constructor(private fb: FormBuilder, private fValidator: FrmValService) {}

  public fileForm: FormGroup = this.fb.group({
    file: ['', [Validators.required]],
  });

  ngOnInit(): void {
    this.responsiveOptions = [
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2,
      },

      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }

  public onFileUpload(event: any) {
    console.log('Upload');
  }

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  public onSveFile() {
    if (!this.onSaveForm(this.fileForm)) return;
    if (!this.selectedId) return;
    console.log(this.fileForm.value);
    //this.talentId.emit(Number(resp.id));
  }

  public isValidFileField(field: string) {
    return this.fValidator.isValidField(this.fileForm, field);
  }

  public openAddFileDialog() {
    this.addFileDialog = true;
  }

  public hideAddFileDialog() {
    this.fileForm.reset();
    this.addFileDialog = false;
  }
}
