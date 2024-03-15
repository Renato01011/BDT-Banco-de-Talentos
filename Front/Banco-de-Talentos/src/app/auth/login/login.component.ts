import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { STORAGE_CURRENT_TOKEN } from '../../core/global/constants/constants';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { FrmValService } from 'src/app/shared/service/frmVal/frm-val.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  mobile: boolean = false;
  invalidCredentials: boolean = false;

  mobile: boolean = false;

  constructor(private router: Router, private formBuilder: FormBuilder, private formValidator: FrmValService, private authService: AuthService) {}

  ngOnInit(): void {
    if (window.screen.width < 1000) {
      this.mobile = true;
    }
    window.onresize = () => (this.mobile = window.innerWidth < 1000);
    this.OnUsernameChange();
    this.OnPasswordChange();
  }

  OnUsernameChange() {
    this.loginForm
      .get('username')!
      .valueChanges
      .subscribe(() => {
        this.invalidCredentials = false;
      });
  }

  OnPasswordChange() {
    this.loginForm
      .get('password')!
      .valueChanges
      .subscribe(() => {
        this.invalidCredentials = false;
      });
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
      this.authService.loginUser(this.loginForm.get('username')!.value, this.loginForm.get('password')!.value).subscribe({
        next: (token) => {
          if (token != null && token.token != '') {
            sessionStorage.setItem(STORAGE_CURRENT_TOKEN, JSON.stringify(token.token));
            this.router.navigateByUrl('/home');
          }
        },
        error: (error) => {
          this.invalidCredentials = true;
        }
      });
    }
    else {
      this.ValidateAllFormFields(this.loginForm);
    }    
  }
}
