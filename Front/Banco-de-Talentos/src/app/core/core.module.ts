import { NgModule, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoaderComponent } from './components/loader/loader.component';

import { MasterService } from 'src/app/core/services/master/master.service';
import { MasterRespConst } from './global/constants/master-resp.constants';
import { ToastComponent } from './components/toast/toast.component';
import { MaterialModule } from '../shared/material/material.module';

@NgModule({
  declarations: [LoaderComponent, ToastComponent],
  imports: [CommonModule, MaterialModule],
  exports: [LoaderComponent, ToastComponent],
})
export class CoreModule implements OnInit {
  constructor(private masterService: MasterService) {
    this.masterService.getLanguages().subscribe((languages) => {
      sessionStorage.setItem(
        MasterRespConst.STORAGE_CURRENT_LANG,
        JSON.stringify(languages)
      );
    });

    this.masterService.getRoles().subscribe((roles) => {
      sessionStorage.setItem(
        MasterRespConst.STORAGE_CURRENT_ROLES,
        JSON.stringify(roles)
      );
    });

    this.masterService.getCurrencies().subscribe((coins) => {
      sessionStorage.setItem(
        MasterRespConst.STORAGE_CURRENT_CURRENCIES,
        JSON.stringify(coins)
      );
    });

    this.masterService.getProfiles().subscribe((profiles) => {
      sessionStorage.setItem(
        MasterRespConst.STORAGE_CURRENT_PROFILES,
        JSON.stringify(profiles)
      );
    });

    this.masterService.getLangProficiency().subscribe((levels) => {
      sessionStorage.setItem(
        MasterRespConst.STORAGE_CURRENT_PROFICIENCY,
        JSON.stringify(levels)
      );
    });

    this.masterService.getCountries().subscribe((countries) => {
      let countryCityList: any[] = [];
      countries.forEach((country) => {
        this.masterService.getCities(country.id).subscribe((city) => {
          countryCityList.push({
            id: country.id,
            country: country.country,
            code: country.code,
            cities: city,
          });
        });
      });
      console.log(countryCityList);
      sessionStorage.setItem(
        MasterRespConst.STORAGE_CURRENT_COUNTRY_CITY,
        JSON.stringify(countryCityList)
      );
    });
  }

  ngOnInit(): void {}
}
