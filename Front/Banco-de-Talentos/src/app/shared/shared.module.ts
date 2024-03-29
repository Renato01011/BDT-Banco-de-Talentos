import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { MaterialModule } from './material/material.module';
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PersProfCrdComponent } from './components/pers-prof-crd/pers-prof-crd.component';
import { ProfPersCrdComponent } from './components/prof-pers-crd/prof-pers-crd.component';
import { CertsPersCrdComponent } from './components/certs-pers-crd/certs-pers-crd.component';
import { SkillsPersCrdComponent } from './components/skills-pers-crd/skills-pers-crd.component';
import { SummPersCrdComponent } from './components/summ-pers-crd/summ-pers-crd.component';
import { ExpPersCrdComponent } from './components/exp-pers-crd/exp-pers-crd.component';
import { EducPersCrdComponent } from './components/educ-pers-crd/educ-pers-crd.component';
import { LangPersCrdComponent } from './components/lang-pers-crd/lang-pers-crd.component';
import { FeedbackCrdComponent } from './components/feedback-crd/feedback-crd.component';
import { PipesModule } from '../core/pipes/pipes.module';

import { ConfirmationService } from 'primeng/api';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { NoResultsComponent } from './components/no-results/no-results.component';

@NgModule({
  declarations: [
    HeaderComponent,
    ControlPanelComponent,
    PersProfCrdComponent,
    ProfPersCrdComponent,
    CertsPersCrdComponent,
    SkillsPersCrdComponent,
    SummPersCrdComponent,
    ExpPersCrdComponent,
    EducPersCrdComponent,
    LangPersCrdComponent,
    FeedbackCrdComponent,
    NoResultsComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    PipesModule,
    PdfViewerModule,
  ],
  exports: [
    HeaderComponent,
    ControlPanelComponent,
    PersProfCrdComponent,
    ProfPersCrdComponent,
    CertsPersCrdComponent,
    SkillsPersCrdComponent,
    SummPersCrdComponent,
    ExpPersCrdComponent,
    EducPersCrdComponent,
    LangPersCrdComponent,
    FeedbackCrdComponent,
    NoResultsComponent,
  ],
  providers: [ConfirmationService],
})
export class SharedModule {}
