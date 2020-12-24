

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
import { PasswordChangeComponent } from './components/password-change/password-change.component';
import { ListOfPrescriptionsComponent } from './components/list-of-prescriptions/list-of-prescriptions.component';
import { ReservedMedicinesComponent } from './components/reserved-medicines/reserved-medicines.component';
import { AllMyMedicinesComponent } from './components/all-my-medicines/all-my-medicines.component';
import { GetMedicinePageComponent } from './components/get-medicine-page/get-medicine-page.component';
import { MyAppointmentsComponent } from './components/my-appointments/my-appointments.component';
import { PenaltyInsightComponent } from './components/penalty-insight/penalty-insight.component';
import { MakeAnAppDComponent } from './components/make-an-app-d/make-an-app-d.component';
import { MakeAnAppPComponent } from './components/make-an-app-p/make-an-app-p.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { HistoryOfAppDComponent } from './components/history-of-app-d/history-of-app-d.component';
import { HistoryOfAppPComponent } from './components/history-of-app-p/history-of-app-p.component';
import { AlergiesComponent } from './components/alergies/alergies.component';
import { SubPharmaciesComponent } from './components/sub-pharmacies/sub-pharmacies.component';
import { MyStatsComponent } from './components/my-stats/my-stats.component';
import {SortPipe} from "./services/SortPipe";
import { MatTableModule } from "@angular/material/table";
import { DermatologistsComponent } from './components/dermatologists/dermatologists.component';
import { ManagePharmacyComponent } from './components/manage-pharmacy/manage-pharmacy.component';
import { ManageDermatologistComponent } from './components/manage-dermatologist/manage-dermatologist.component';
import {MatDialogModule, MatDialog} from '@angular/material/dialog';
import { NewPharmacyComponent } from './components/new-pharmacy/new-pharmacy.component';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { PharmacyDetailsComponent } from './components/pharmacy-details/pharmacy-details.component';
import { RegisterDermatologistComponent } from './components/register-dermatologist/register-dermatologist.component';
import { AddDermComponent } from './components/add-derm/add-derm.component';
import { AddPharmComponent } from './components/add-pharm/add-pharm.component';





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
    PasswordChangeComponent,
    ListOfPrescriptionsComponent,
    ReservedMedicinesComponent,
    AllMyMedicinesComponent,
    GetMedicinePageComponent,
    MyAppointmentsComponent,
    PenaltyInsightComponent,
    MakeAnAppDComponent,
    MakeAnAppPComponent,
    ComplaintComponent,
    HistoryOfAppDComponent,
    HistoryOfAppPComponent,
    AlergiesComponent,
    SubPharmaciesComponent,
    MyStatsComponent,
    SortPipe,
    DermatologistsComponent,
    ManagePharmacyComponent,
    ManageDermatologistComponent,
    NewPharmacyComponent,
    PharmacyDetailsComponent,
    RegisterDermatologistComponent,
    AddDermComponent,
    AddPharmComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatDialogModule,
    BrowserAnimationsModule,
    NoopAnimationsModule
  ],
  entryComponents: [
    NewPharmacyComponent
  ],

  providers: [
    SortPipe,
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
