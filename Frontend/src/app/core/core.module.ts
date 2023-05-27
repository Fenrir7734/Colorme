import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthComponent} from "./auth/auth.component";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {
    path: "login",
    component: AuthComponent
  }
]

@NgModule({
  declarations: [
    AuthComponent
  ],
  exports: [
    AuthComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    CommonModule
  ]
})
export class CoreModule { }
