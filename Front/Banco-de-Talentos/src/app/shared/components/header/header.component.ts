import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'shared-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  profileOpItems: MenuItem[] = [];

  constructor() {}

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
      },
    ];
  }
}
