import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { tap } from 'rxjs';
import { LoaderService } from 'src/app/core/services/loader/loader.service';
import { FilterRequest } from 'src/app/shared/models/interfaces/filterReq.interfaces';
import { FilterResponse } from 'src/app/shared/models/interfaces/filterResp.interfaces';
import {
  Document,
  EducationalExperience,
  Feedbacks,
  LanguageLevel,
  SoftSkill,
  TalentResponse,
  TechnicalAbility,
  WorkExperience,
} from 'src/app/shared/models/interfaces/talentResp.interfaces';
import { AuthService } from '../../../core/services/auth/auth.service';
import { FilterService } from '../../../core/services/filter/filter.service';
import { TalentService } from '../../../core/services/talent/talent.service';
import { ToastService } from '../../../core/services/toast/toast.service';
import { CustomTalent } from '../../../shared/models/interfaces/customTalent.interfaces';
import { Paginator } from 'primeng/paginator';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  public isRecruiter: boolean = false;

  public filterReq: FilterRequest = {
    userId: this.authService.idUser,
    habilities: '',
    languageIds: '',
    levelIds: '',
    nameJobTitle: '',
    userListIds: '',
    pagina: 1,
  };

  public totalTalents: number = 0;
  @ViewChild('paginator') paginator!: Paginator;

  public selId?: number;
  public talentId?: number;
  public talents: FilterResponse[] = [];

  public customTalent?: CustomTalent;
  public description: string = '';
  public availability: string = '';
  public documents: Document[] = [];
  public educExp: EducationalExperience[] = [];
  public langProficiency: LanguageLevel[] = [];
  public softSkills: SoftSkill[] = [];
  public techSkills: TechnicalAbility[] = [];
  public workExp: WorkExperience[] = [];
  public feedbacks: Feedbacks[] = [];
  public iconCoin: string = '';

  constructor(
    private filterService: FilterService,
    private loaderService: LoaderService,
    private talentService: TalentService,
    private authService: AuthService,
    private router: Router,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getTalentList(this.filterReq);
    this.isRecruiter = this.authService.isRecruiter;
  }

  resetPage() {
    if (this.paginator) {
      this.paginator.changePage(0);
    }
  }

  paginate(event: any) {
    //event.first = Index of the first record
    //event.rows = Number of rows to display in new page
    //event.page = Index of the new page
    //event.pageCount = Total number of pages
    this.filterReq.pagina = event.page + 1;
    this.getTalentList(this.filterReq);
  }

  public filterTalent(filter: FilterRequest): void {
    this.filterReq = filter;
    this.selId = undefined;
    this.getTalentList(this.filterReq);
    if (this.totalTalents > 0) {
      this.resetPage();
    }
  }

  public saveChanges(id: number): void {
    this.searchByTalentId(id);
    this.getTalentList(this.filterReq);
  }

  private getTalentList(filter: FilterRequest): void {
    this.loaderService.showLoader();
    this.filterService.filterTalent(filter).subscribe({
      next: (talents) => {
        this.totalTalents = talents.total;
        this.talents = talents.list;
        // this.firstCall(talents);
        this.loaderService.hideLoader();
      },
    });
  }

  public searchByTalentId(id: number): void {
    if (isNaN(id)) return;
    this.loaderService.showLoader();
    this.talentService
      .getTalentById(id, this.authService.idUser)
      .pipe(
        tap((talent) => this.updateTalentData(talent)),
        tap((talent) => this.newTalent(talent))
      )
      .subscribe({
        next: (talent) => {
          this.selId = talent.idTalent;
          this.loaderService.hideLoader();
        },
      });
  }

  private updateTalentData(talent: TalentResponse): void {
    const [, ...certifications] = talent.documents;
    this.documents = certifications;
    this.educExp = talent.educationalExperiences;
    this.langProficiency = talent.languageLevels;
    this.softSkills = talent.softSkills;
    this.techSkills = talent.technicalAbilities;
    this.workExp = talent.workExperiences;
    this.description = talent.description;
    this.availability = talent.disponibilidad;
    this.feedbacks = talent.feedbacks;
    this.iconCoin = talent.iconCoin;
  }

  public firstCall(talents: FilterResponse[]): void {
    if (talents.length === 0) return;
    if (this.selId === undefined) {
      const [firstObjeto] = talents;
      const firstId = firstObjeto.id;
      this.searchByTalentId(firstId);
    }
  }

  public newTalent(talent: TalentResponse): void {
    const [resume] = talent.documents;
    const {
      idTalent,
      name,
      surname,
      secondSurname,
      profilePicture,
      initialSalaryRxH: montoInicialRxH,
      finalSalaryRxH: montoFinalRxH,
      initialSalaryPlanilla: initialSalary,
      finalSalaryPlanilla: finalSalary,
      phone,
      linkedin,
      github,
      avgRating,
      miscData,
      feedbacks,
      userListTalent,
      email,
      iconCoin,
    } = talent;

    this.customTalent = {
      idTalent,
      name,
      surname,
      secondSurname,
      profilePicture,
      initialSalaryRxH: montoInicialRxH,
      finalSalaryRxH: montoFinalRxH,
      initialSalaryPlanilla: initialSalary,
      finalSalaryPlanilla: finalSalary,
      phone,
      linkedin,
      github,
      avgRating,
      miscData,
      feedbacks,
      userListTalent,
      resume,
      email,
      iconCoin,
    };
  }

  public get isListEmpty(): boolean {
    return this.talents.length !== 0;
  }
}
