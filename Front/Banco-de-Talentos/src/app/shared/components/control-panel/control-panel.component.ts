import {
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import { MasterService } from '../../../core/services/master/master.service';
import { TechSkills } from '../../models/interfaces/techSkill.interfaces';
import {
  LangProficiencyModel,
  LanguageModel,
} from '../../models/interfaces/master.interfaces';
import { FilterRequest } from '../../models/interfaces/filterReq.interfaces';
import { FilterService } from 'src/app/core/services/filter/filter.service';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { UserList } from '../../models/interfaces/userList.interfaces';
import { UserService } from '../../service/user/user.service';
import { OverlayPanel } from 'primeng/overlaypanel';
import { LoaderService } from 'src/app/core/services/loader/loader.service';

@Component({
  selector: 'shared-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.scss'],
})
export class ControlPanelComponent implements OnInit {
  @ViewChild('techSkills') overlayPanelTech!: OverlayPanel;
  @ViewChild('proficiencyOP') overlayPanelProficiency!: OverlayPanel;
  @ViewChild('favorite') overlayPanelFavorite!: OverlayPanel;

  skills: TechSkills[] = [];
  language: LanguageModel[] = [];
  proficiency: LangProficiencyModel[] = [];

  favorites: UserList[] = [];

  selectedTechSks: string[] = [];
  selectedIdLanguage: string = '';
  term: string = '';

  public idUser?: number;

  public isRecruiter: boolean = false;

  public filterReq: FilterRequest = {
    userId: this.authService.idUser,
    habilities: '',
    languageIds: '',
    levelIds: '',
    nameJobTitle: '',
    userListIds: '',
  };

  selectedIdProficiency?: number;
  selectedFavoriteId?: number;

  @Output()
  public onFilterReqVal = new EventEmitter<FilterRequest>();

  constructor(
    private router: Router,
    private masterService: MasterService,
    private filterService: FilterService,
    private authService: AuthService,
    private loaderService: LoaderService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.isRecruiter = this.authService.isRecruiter;
    this.idUser = this.authService.idUser;
  }

  onFavorite() {
    this.getFavorites(this.authService.idUser);
  }

  deselectFavorite(item: number) {
    if (this.selectedFavoriteId === item) {
      this.selectedFavoriteId = undefined;
    }
    if (this.selectedIdProficiency === item) {
      this.selectedIdLanguage = '';
      this.selectedIdProficiency = undefined;
    }
  }

  handleTechBtnClick(event: any) {
    this.masterService.cacheStorage.byTechSkill.techSkills = [];
    this.checkTechSkills();
    this.overlayPanelTech.toggle(event);
  }

  handleProficiencyBtnClick(event: any) {
    this.checkProficiency();
    this.overlayPanelProficiency.toggle(event);
  }

  handleFavoriteBtnClick(event: any) {
    this.checkFavorites();
    this.overlayPanelFavorite.toggle(event);
  }

  emitFilter() {
    const skillsStr = this.selectedTechSks.join(', ');
    this.filterReq.habilities = skillsStr;
    this.filterReq.languageIds =
      this.selectedIdProficiency !== undefined ? '2' : '';
    this.filterReq.levelIds = `${this.selectedIdProficiency ?? ''}`;
    this.filterReq.nameJobTitle = this.term;
    this.filterReq.userListIds = `${this.selectedFavoriteId ?? ''}`;
    this.onFilterReqVal.emit(this.filterReq);
  }

  onNewTalent() {
    this.router.navigateByUrl('/home/talent');
  }

  private getTechSkills(): void {
    this.loaderService.showLoader();
    this.masterService.getTechSkills().subscribe({
      next: (skills) => {
        this.skills = skills;
        this.loaderService.hideLoader();
      },
    });
  }

  private getProficiencies(): void {
    this.masterService.getLangProficiency().subscribe({
      next: (proficiency) => {
        this.proficiency = proficiency;
      },
    });
  }

  private getFavorites(userId: number) {
    this.loaderService.showLoader();
    this.userService.getUserLists(userId).subscribe({
      next: (favorites) => {
        this.favorites = favorites;
        this.loaderService.hideLoader();
      },
    });
  }

  public get isCacheFavoritesEmpty(): boolean {
    return (
      !this.userService.favoritesCache ||
      this.userService.favoritesCache.length === 0
    );
  }

  private checkFavorites(): void {
    if (!this.authService.idUser) return;
    if (this.isCacheFavoritesEmpty) {
      this.getFavorites(this.authService.idUser);
    } else {
      this.favorites = this.userService.favoritesCache;
    }
  }

  public get isSkillsListEmpty(): boolean {
    return !this.skills || this.skills.length === 0;
  }

  private get isCacheSkillsEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byTechSkill.techSkills ||
      this.masterService.cacheStorage.byTechSkill.techSkills.length === 0
    );
  }

  private checkTechSkills(): void {
    if (this.isCacheSkillsEmpty) {
      this.getTechSkills();
    } else {
      const cacheTecSkills =
        this.masterService.cacheStorage.byTechSkill.techSkills;
      this.skills = cacheTecSkills;
    }
  }

  private get isCacheProficiencyEmpty(): boolean {
    return (
      !this.masterService.cacheStorage.byLangProficiency.proficiencies ||
      this.masterService.cacheStorage.byLangProficiency.proficiencies.length ===
        0
    );
  }

  private checkProficiency() {
    if (this.isCacheProficiencyEmpty) {
      this.getProficiencies();
    } else {
      const cacheProficiencies =
        this.masterService.cacheStorage.byLangProficiency.proficiencies;
      this.proficiency = cacheProficiencies;
    }
  }

  public get totalMsg(): string {
    return this.filterService.resultMsg;
  }

  public get total(): number {
    return this.filterService.total;
  }
}
