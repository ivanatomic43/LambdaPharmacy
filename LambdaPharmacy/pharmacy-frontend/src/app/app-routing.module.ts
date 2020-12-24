import { AddPharmComponent } from './components/add-pharm/add-pharm.component';
import { AddDermComponent } from './components/add-derm/add-derm.component';
import { RegisterDermatologistComponent } from './components/register-dermatologist/register-dermatologist.component';

import { PharmacyDetailsComponent } from './components/pharmacy-details/pharmacy-details.component';
import { NewPharmacyComponent } from './components/new-pharmacy/new-pharmacy.component';
import { ManageDermatologistComponent } from './components/manage-dermatologist/manage-dermatologist.component';
import { ManagePharmacyComponent } from './components/manage-pharmacy/manage-pharmacy.component';
import { DermatologistsComponent } from './components/dermatologists/dermatologists.component';
import { MyStatsComponent } from './components/my-stats/my-stats.component';
import { SubPharmaciesComponent } from './components/sub-pharmacies/sub-pharmacies.component';
import { AlergiesComponent } from './components/alergies/alergies.component';
import { HistoryOfAppPComponent } from './components/history-of-app-p/history-of-app-p.component';
import { HistoryOfAppDComponent } from './components/history-of-app-d/history-of-app-d.component';
import { MakeAnAppPComponent } from './components/make-an-app-p/make-an-app-p.component';
import { ListOfPrescriptionsComponent } from './components/list-of-prescriptions/list-of-prescriptions.component';
import { PasswordChangeComponent } from './components/password-change/password-change.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {HomepageComponent} from './components/homepage/homepage.component';
import {PatientPageComponent} from './components/patient-page/patient-page.component';
import {PatientProfileComponent} from './components/patient-profile/patient-profile.component';
import { ReservedMedicinesComponent } from './components/reserved-medicines/reserved-medicines.component';
import { AllMyMedicinesComponent } from './components/all-my-medicines/all-my-medicines.component';
import { GetMedicinePageComponent } from './components/get-medicine-page/get-medicine-page.component';
import { MyAppointmentsComponent } from './components/my-appointments/my-appointments.component';
import { PenaltyInsightComponent } from './components/penalty-insight/penalty-insight.component';
import { MakeAnAppDComponent } from './components/make-an-app-d/make-an-app-d.component';
import { ComplaintComponent } from './components/complaint/complaint.component';

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
  {path: 'my-stats', component: MyStatsComponent},
  {path: 'dermatologists', component: DermatologistsComponent},
  {path: 'manage-pharmacy', component: ManagePharmacyComponent},
  {path: 'manage-dermatologist', component: ManageDermatologistComponent},
  {path: 'new-pharmacy', component: NewPharmacyComponent},
  {path: 'pharmacy-details',
  children : [
    {
      path: ':id',
      component: PharmacyDetailsComponent
    }
  ]
  },
  {path: 'add-derm',
    children: [
      {
        path: ':id',
        component: AddDermComponent
      }
    ]
  },
  { path: 'add-pharm',
      children :[
        {
          path: ':id',
          component: AddPharmComponent
        }
      ]
  },

  {path: 'register-dermatologist', component: RegisterDermatologistComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [

];
