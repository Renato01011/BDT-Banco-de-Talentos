import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  mobile: boolean = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    if (window.screen.width < 1000) {
      this.mobile = true;
    }
    window.onresize = () => (this.mobile = window.innerWidth < 1000);
  }

  onLogin() {
    this.router.navigateByUrl('/home');
  }
}
