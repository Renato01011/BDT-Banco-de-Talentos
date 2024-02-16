import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { MasterService } from 'src/app/core/services/master/master.service';

@Component({
  selector: 'app-new-talent',
  templateUrl: './new-talent.component.html',
  styleUrls: ['./new-talent.component.scss']
})
export class NewTalentComponent implements OnInit {

  profiles: any[] = [];
  levels: any[] = [];
  languages: any[] = [];
  coins: any[] = [];

  selectedCoin: any = null;

  constructor(private router: Router, private masterService: MasterService) {

    this.masterService.getLanguages().subscribe(languages => {
      this.languages = languages;
    });
    this.masterService.getLangProficiency().subscribe(levels => {
      this.levels = levels;
    });
    this.masterService.getProfiles().subscribe(profiles => {
      this.profiles = profiles;
    });
    this.masterService.getCurrencies().subscribe(coins => {
      this.coins = coins;
    });

  }

  ngOnInit(): void {
    
  }

  onVolver() {
    this.router.navigateByUrl('/home/dashboard/list');
  }

}
