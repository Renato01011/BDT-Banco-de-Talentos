import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth/auth.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  public username: string = '';
  public name: string = '';
  public isRecruiter: boolean = false;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.username = this.authService.username;
    this.name = this.authService.name;
    this.isRecruiter = this.authService.isRecruiter;
  }
}
