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
import { ListOfPrescriptionsComponent } from './list-of-prescriptions/list-of-prescriptions.component';
import { ReservedMedicinesComponent } from './reserved-medicines/reserved-medicines.component';
import { AllMyMedicinesComponent } from './all-my-medicines/all-my-medicines.component';
import { GetMedicinePageComponent } from './get-medicine-page/get-medicine-page.component';
import { MyAppointmentsComponent } from './my-appointments/my-appointments.component';
import { PenaltyInsightComponent } from './penalty-insight/penalty-insight.component';
import { MakeAnAppDComponent } from './make-an-app-d/make-an-app-d.component';
import { MakeAnAppPComponent } from './make-an-app-p/make-an-app-p.component';
import { ComplaintComponent } from './complaint/complaint.component';
import { HistoryOfAppDComponent } from './history-of-app-d/history-of-app-d.component';
import { HistoryOfAppPComponent } from './history-of-app-p/history-of-app-p.component';
import { AlergiesComponent } from './alergies/alergies.component';
import { SubPharmaciesComponent } from './sub-pharmacies/sub-pharmacies.component';
import { MyComponent } from './my/my.component';
import { MyStatsComponent } from './my-stats/my-stats.component';





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
