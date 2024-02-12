import { NgModule } from '@angular/core';

import { StyleClassModule } from 'primeng/styleclass';
import { RatingModule } from 'primeng/rating';
import { DialogModule } from 'primeng/dialog';
import { ChipModule } from 'primeng/chip';
import { ListboxModule } from 'primeng/listbox';
import { ChipsModule } from 'primeng/chips';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ToastModule } from 'primeng/toast';
import { MultiSelectModule } from 'primeng/multiselect';
import { MenuModule } from 'primeng/menu';
import { MenubarModule } from 'primeng/menubar';
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';
import { InputTextModule } from 'primeng/inputtext';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { CardModule } from 'primeng/card';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { CheckboxModule } from 'primeng/checkbox';
import { DropdownModule } from 'primeng/dropdown';
import { ImageModule } from 'primeng/image';

@NgModule({
  exports: [
    ButtonModule,
    PanelModule,
    ToolbarModule,
    SplitButtonModule,
    InputTextModule,
    ScrollPanelModule,
    CardModule,
    ToggleButtonModule,
    CheckboxModule,
    DropdownModule,
    StyleClassModule,
    MenuModule,
    AvatarModule,
    AvatarGroupModule,
    MenubarModule,
    MultiSelectModule,
    OverlayPanelModule,
    ToastModule,
    ChipsModule,
    ListboxModule,
    RatingModule,
<<<<<<< Updated upstream
    ImageModule,
=======
    DialogModule,
    ChipModule,
>>>>>>> Stashed changes
  ],
})
export class MaterialModule {}
