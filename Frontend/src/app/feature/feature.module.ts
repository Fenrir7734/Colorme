import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import {RouterLink, RouterLinkActive, RouterModule, Routes} from "@angular/router";
import {CoreModule} from "../core/core.module";
import {authGuard} from "../core/guards/auth.guard";

const routes: Routes = [
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "user",
    component: UserComponent,
    canActivate: [authGuard]
  }
];

@NgModule({
  declarations: [
    UserComponent,
    HomeComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule,
    RouterLink,
    RouterLinkActive,
    CoreModule
  ]
})
export class FeatureModule { }
