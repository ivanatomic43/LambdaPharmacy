import { MyStatsComponent } from './my-stats/my-stats.component';
import { SubPharmaciesComponent } from './sub-pharmacies/sub-pharmacies.component';
import { AlergiesComponent } from './alergies/alergies.component';
import { HistoryOfAppPComponent } from './history-of-app-p/history-of-app-p.component';
import { HistoryOfAppDComponent } from './history-of-app-d/history-of-app-d.component';
import { MakeAnAppPComponent } from './make-an-app-p/make-an-app-p.component';
import { ListOfPrescriptionsComponent } from './list-of-prescriptions/list-of-prescriptions.component';
import { PasswordChangeComponent } from './components/password-change/password-change.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {HomepageComponent} from './components/homepage/homepage.component';
import {PatientPageComponent} from './components/patient-page/patient-page.component';
import {PatientProfileComponent} from './components/patient-profile/patient-profile.component';
import { ReservedMedicinesComponent } from './reserved-medicines/reserved-medicines.component';
import { AllMyMedicinesComponent } from './all-my-medicines/all-my-medicines.component';
import { GetMedicinePageComponent } from './get-medicine-page/get-medicine-page.component';
import { MyAppointmentsComponent } from './my-appointments/my-appointments.component';
import { PenaltyInsightComponent } from './penalty-insight/penalty-insight.component';
import { MakeAnAppDComponent } from './make-an-app-d/make-an-app-d.component';
import { ComplaintComponent } from './complaint/complaint.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomepageComponent},
  {path:  'patient', component: PatientPageComponent},
  {path: 'patient-profile', component: PatientProfileComponent},
  {path: 'editProfile', component: EditProfileComponent},
  {path: 'changePassword', component: PasswordChangeComponent},
  {path: 'list-of-prescriptions', component: ListOfPrescriptionsComponent },
  {path: 'reserved-medicines', component: ReservedMedicinesComponent },
  {path: 'all-my-medicines', component: AllMyMedicinesComponent },
  {path: 'get-medicine-page', component: GetMedicinePageComponent },
  {path: 'my-appointments', component: MyAppointmentsComponent },
  {path: 'penalty-insight', component: PenaltyInsightComponent },
  {path: 'make-an-app-d', component: MakeAnAppDComponent },
  {path: 'make-an-app-p', component:MakeAnAppPComponent },
  {path: 'complaint', component:  ComplaintComponent},
  {path: 'history-of-app-d', component:  HistoryOfAppDComponent},
  {path: 'history-of-app-p', component: HistoryOfAppPComponent},
  {path: 'alergies', component: AlergiesComponent},
  {path: 'sub-pharmacies', component: SubPharmaciesComponent },
  {path: 'my-stats', component: MyStatsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [

];
