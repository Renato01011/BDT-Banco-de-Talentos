import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Authority } from '../../models/enums/authority.enum';

@Component({
  selector: 'shared-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  @Input()
  public username: string = '';
  @Input()
  public name: string = '';
  @Input()
  public isRecruiter: boolean = false;

  profileOpItems: MenuItem[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.profileOpItems = [
      {
        label: 'Edit Profile',
        icon: 'pi pi-fw pi-user',
      },
      {
        label: 'Settings & Privacy',
        icon: 'pi pi-fw pi-cog',
      },
      {
        label: 'Help & Support',
        icon: 'pi pi-fw pi-question-circle',
      },
      {
        label: 'Logout',
        icon: 'pi pi-fw pi-sign-out',
        command: (event) => this.logout(),
      },
    ];
  }

  private logout() {
    sessionStorage.clear();
    this.router.navigateByUrl('/auth/login');
  }

  public get recruiterOrVisitant(): string {
    return this.isRecruiter ? Authority.RECLUTADOR : Authority.VISITANTE;
  }
}
