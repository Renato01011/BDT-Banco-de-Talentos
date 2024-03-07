import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { ToastService } from 'src/app/core/services/toast/toast.service';
import { FrmValService } from 'src/app/shared/service/frmVal/frm-val.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  mobile: boolean = false;

  loginForm = this.formBuilder.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  constructor(private router: Router, private formBuilder: FormBuilder, private formValidator: FrmValService, private authService: AuthService, private toastService: ToastService) {}

  ngOnInit(): void {
    if (window.screen.width < 1000) {
      this.mobile = true;
    }
    window.onresize = () => (this.mobile = window.innerWidth < 1000);
  }

  ValidateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach((field) => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.ValidateAllFormFields(control);
      }
    });
  }

  isValidField(field: string) {
    return this.formValidator.isValidField(this.loginForm, field);
  }

  onLogin() {
    if (this.loginForm.valid) {
      if (this.authService.loginUser(this.loginForm.get('username')!.value, this.loginForm.get('password')!.value)) {
        this.router.navigateByUrl('/home');
      }
      else {
        this.toastService.addProperties(
          'error',
          'Ocurrio un error',
          'Ingrese credenciales validas'
        );
      }
    }
    else {
      this.ValidateAllFormFields(this.loginForm);
    }    
  }
}
