import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { tap } from 'rxjs';
import { CustomTalent } from '../../../shared/models/interfaces/customTalent.interfaces';
import { FilterRequest } from 'src/app/shared/models/interfaces/filterReq.interfaces';
import { FilterResponse } from 'src/app/shared/models/interfaces/filterResp.interfaces';
import { FilterService } from '../../../core/services/filter/filter.service';
import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { TalentService } from '../../../core/services/talent/talent.service';
import { ToastService } from '../../../core/services/toast/toast.service';
import { AuthService } from '../../../core/services/auth/auth.service';
import {
  Document,
  EducationalExperience,
  LanguageLevel,
  SoftSkill,
  TalentResponse,
  TechnicalAbility,
  WorkExperience,
} from 'src/app/shared/models/interfaces/talentResp.interfaces';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  public isRecruiter: boolean = false;

  public filterReq: FilterRequest = {
    habilities: '',
    languageIds: '',
    levelIds: '',
    nameJobTitle: '',
  };

  public selId?: number;
  public talentId?: number;
  public talents: FilterResponse[] = [];

  public customTalent?: CustomTalent;
  public description: string = '';
  public documents: Document[] = [];
  public educExp: EducationalExperience[] = [];
  public langProficiency: LanguageLevel[] = [];
  public softSkills: SoftSkill[] = [];
  public techSkills: TechnicalAbility[] = [];
  public workExp: WorkExperience[] = [];

  constructor(
    private filterService: FilterService,
    private loaderService: LoaderService,
    private router: Router,
    private talentService: TalentService,
    private toastService: ToastService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getTalentList(this.filterReq);

    this.isRecruiter = this.authService.isRecruiter;
    console.log(this.isRecruiter);
  }

  public filterTalent(filter: FilterRequest): void {
    this.filterReq = filter;
    this.getTalentList(this.filterReq);
  }

  private getTalentList(filter: FilterRequest): void {
    this.loaderService.showLoader();
    this.filterService.filterTalent(filter).subscribe({
      next: (talents) => {
        this.talents = talents;
        this.firstCall();
        this.loaderService.hideLoader();
        this.totalTalents(talents);
      },
    });
  }

  public searchByTalentId(id: number): void {
    this.loaderService.showLoader();
    this.talentService
      .getTalentById(id)
      .pipe(
        tap((talent) => (this.documents = talent.documents)),
        tap((talent) => (this.educExp = talent.educationalExperiences)),
        tap((talent) => (this.langProficiency = talent.languageLevels)),
        tap((talent) => (this.softSkills = talent.softSkills)),
        tap((talent) => (this.techSkills = talent.technicalAbilities)),
        tap((talent) => (this.workExp = talent.workExperiences)),
        tap((talent) => (this.description = talent.description)),
        tap((talent) => this.newTalent(talent))
      )
      .subscribe({
        next: (talent) => {
          this.selId = talent.idTalent;
          this.loaderService.hideLoader();
        },
      });
  }

  public firstCall(): void {
    if (this.talents && this.talents.length > 0) {
      const [firstObjeto] = this.talents;
      const firstId = firstObjeto.id;
      this.searchByTalentId(firstId);
    }
  }

  public saveChanges(id: number): void {
    this.searchByTalentId(id);
    this.getTalentList(this.filterReq);
  }

  public newTalent(talent: TalentResponse): void {
    const {
      idTalent,
      name,
      surname,
      secondSurname,
      profilePicture,
      initialSalary,
      finalSalary,
      phone,
      linkedin,
      github,
      avgRating,
      miscData,
      feedbacks,
    } = talent;

    this.customTalent = {
      idTalent,
      name,
      surname,
      secondSurname,
      profilePicture,
      initialSalary,
      finalSalary,
      phone,
      linkedin,
      github,
      avgRating,
      miscData,
      feedbacks,
    };
  }

  public get isListEmpty(): boolean {
    return this.talents.length !== 0;
  }

  public totalTalents(talents: FilterResponse[]): void {
    const total = talents.length;
    if (total === 1) {
      this.toastService.addProperties(
        'info',
        'Info',
        `¡Hemos encontrado un resultado para tu búsqueda!`
      );
    } else {
      this.toastService.addProperties(
        'info',
        'Info',
        `¡Hemos encontrado ${total} resultados para tu búsqueda!`
      );
    }
  }
}
