import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Document } from '../../models/interfaces/talentResp.interfaces';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FrmValService } from '../../service/frmVal/frm-val.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { AddInfoService } from '../../service/addInfo/add-info.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';

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

  public isRecruiter: boolean = false;

  public fileText: string = 'Sube un archivo';
  public fileDetailsText: string = 'PDF (max. 5MB)';
  public base64file?: string;
  public fileUploaded: boolean = false;
  public addFileDialog: boolean = false;

  public responsiveOptions: any[] = [];

  public display: boolean = false;

  public pdfSrc: string = '';

  constructor(
    private fb: FormBuilder,
    private fValidator: FrmValService,
    private toastService: ToastService,
    private addInfoService: AddInfoService,
    private authService: AuthService
  ) {}

  public fileForm: FormGroup = this.fb.group({
    file: ['', [Validators.required]],
    fileType: ['PDF'],
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
    this.isRecruiter = this.authService.isRecruiter;
  }

  public onFileUpload(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      if (file.size > 50000000) {
        this.toastService.addProperties(
          'error',
          'Ocurrió un error',
          'Documento es demasiado pesado'
        );
        return;
      }
      if (file.type != 'application/pdf') {
        this.toastService.addProperties(
          'error',
          'Ocurrió un error',
          'Documento no es PDF'
        );
        return;
      }
      this.fileUploaded = true;

      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      fileReader.onloadend = () => {
        this.base64file = fileReader.result as string;
      };
      this.fileText = 'Archivo Correctamente Subido';
      this.fileDetailsText = file.name;
    } else {
      this.toastService.addProperties(
        'error',
        'Ocurrió un error',
        'Error al subir PDF, vuelva a intentar'
      );
    }
  }

  private onSaveForm(form: FormGroup): boolean {
    if (form.invalid) {
      form.markAllAsTouched();
      return false;
    }
    return true;
  }

  public onSveFile() {
    if (
      !this.onSaveForm(this.fileForm) ||
      !this.selectedId ||
      !this.base64file
    ) {
      return;
    }
    const { fileType } = this.fileForm.value;
    const body = {
      nombre: this.extractFileName(this.fileDetailsText),
      tipoArchivo: fileType,
      archivo: this.base64file.split(',')[1],
    };
    this.addInfoService.addFile(body, this.selectedId).subscribe({
      next: (resp) => {
        this.toastService.addProperties(
          'success',
          'Se agregó correctamente',
          resp.message
        );
        this.talentId.emit(Number(resp.id));
        this.hideAddFileDialog();
      },
    });
  }

  public isValidFileField(field: string) {
    return this.fValidator.isValidField(this.fileForm, field);
  }

  public openAddFileDialog() {
    this.addFileDialog = true;
  }

  public hideAddFileDialog() {
    this.fileForm.reset({ fileType: 'PDF' });
    this.fileText = 'Sube un archivo';
    this.fileDetailsText = 'PDF (max. 5MB)';
    this.fileUploaded = false;
    this.addFileDialog = false;
  }

  extractFileName(fileName: string): string {
    const index = fileName.lastIndexOf('.pdf');
    if (index !== -1) {
      return fileName.substring(0, index);
    } else {
      return fileName;
    }
  }

  public showDialog(id: number) {
    console.log(this.documents);
    this.loadPdf(id);
    this.display = true;
  }

  private loadPdf(id: number): void {
    const resp = this.findCertsById(id);
    const base64Pdf = resp.document;
    this.pdfSrc = 'data:application/pdf;base64,' + base64Pdf;
  }

  private findCertsById(id: number): Document {
    const certs = this.documents.find((cert) => cert.idDocument === id)!;
    return certs;
  }
}
