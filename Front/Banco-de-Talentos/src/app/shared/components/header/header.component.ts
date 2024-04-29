import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Authority } from '../../models/enums/authority.enum';
import { UserService } from '../../service/user/user.service';

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

  // public picture: string = '';

  profileOpItems: MenuItem[] = [];

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.profileOpItems = [
      {
        label: 'Logout',
        icon: 'pi pi-fw pi-sign-out',
        command: (event) => this.logout(),
      },
    ];
    // this.getPicture();
  }

  // public getPicture() {
  //   this.userService.getPicture(this.username).subscribe({
  //     next: (resp) => (this.picture = resp.img),
  //   });
  // }

  private logout() {
    sessionStorage.clear();
    this.router.navigateByUrl('/auth/login');
  }

  public get recruiterOrVisitor(): string {
    return this.isRecruiter ? Authority.RECLUTADOR : Authority.VISITANTE;
  }
}
