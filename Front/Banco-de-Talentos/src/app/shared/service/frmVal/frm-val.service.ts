import { Injectable } from '@angular/core';
import { AbstractControl, FormGroup, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class FrmValService {
  public isValidField(form: FormGroup, field: string) {
    return form.controls[field].errors && form.controls[field].touched;
  }

  private getFieldErrors(form: FormGroup, field: string): any {
    if (!form.controls[field]) return null;
    return form.controls[field].errors ?? {};
  }

  public isRequiredErr(form: FormGroup, field: string): string | null {
    const errors = this.getFieldErrors(form, field);
    if (errors && 'required' in errors) {
      return 'Este campo no puede estar vacío.';
    }
    return null;
  }

  public isMinLengthErr(form: FormGroup, field: string): string | null {
    const errors = this.getFieldErrors(form, field);
    if (errors && 'minlength' in errors) {
      return `Mínimo ${errors['minlength'].requiredLength} caracteres`;
    }
    return null;
  }

  public isMaxLengthErr(form: FormGroup, field: string): string | null {
    const errors = this.getFieldErrors(form, field);
    if (errors && 'maxlength' in errors) {
      return `Máximo ${errors['maxlength'].requiredLength} caracteres`;
    }
    return null;
  }

  public isFieldOneLessFieldTwo(field1: string, field2: string) {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const fieldValue1 = parseFloat(formGroup.get(field1)?.value);
      const fieldValue2 = parseFloat(formGroup.get(field2)?.value);

      if (
        !isNaN(fieldValue1) &&
        !isNaN(fieldValue2) &&
        fieldValue1 >= fieldValue2
      ) {
        formGroup.get(field2)?.setErrors({ notGreater: true });
        return { notGreater: true };
      }

      formGroup.get(field2)?.setErrors(null);
      return null;
    };
  }

  public compareDates(dateField1: string, dateField2: string) {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const dateValue1 = new Date(formGroup.get(dateField1)?.value);
      const dateValue2 = new Date(formGroup.get(dateField2)?.value);

      if (
        !isNaN(dateValue1.getTime()) &&
        !isNaN(dateValue2.getTime()) &&
        dateValue1 >= dateValue2
      ) {
        formGroup.get(dateField2)?.setErrors({ notGreater: true });
        return { notGreater: true };
      }

      formGroup.get(dateField2)?.setErrors(null);
      return null;
    };
  }
}
