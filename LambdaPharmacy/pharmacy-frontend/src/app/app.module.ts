import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import {Routes} from '@angular/router';
import { SearchComponent } from './components/search/search.component';
import { ListOfMedicinesComponent } from './components/list-of-medicines/list-of-medicines.component';
import { ListOfPharmaciesComponent } from './components/list-of-pharmacies/list-of-pharmacies.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { PatientPageComponent } from './components/patient-page/patient-page.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './services/AuthService';
import {FooService} from './services/FooService';
import {ConfigService} from './services/ConfigService';
import {ApiService} from './services/ApiService';
import {UserService} from './services/UserService';
import {TokenInterceptor} from './interceptor/TokenInterceptor';
import { PatientProfileComponent } from './components/patient-profile/patient-profile.component';
import { AlertComponent } from './alert/alert.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';





@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    HomepageComponent,
    SearchComponent,
    ListOfMedicinesComponent,
    ListOfPharmaciesComponent,
    PatientPageComponent,
    PatientProfileComponent,
    AlertComponent,
    EditProfileComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
