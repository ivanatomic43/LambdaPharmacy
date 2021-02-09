import { LoyaltyProgramComponent } from './components/loyalty-program/loyalty-program.component';
import { StatisticComponent } from './components/statistic/statistic.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { OrdersComponent } from './components/orders/orders.component';
import { EditMedicineComponent } from './components/edit-medicine/edit-medicine.component';
import { RegisterSupplierComponent } from './components/register-supplier/register-supplier.component';
import { RegisterSysAdminComponent } from './components/register-sys-admin/register-sys-admin.component';
import { VacationsComponent } from './components/vacations/vacations.component';
import { PricelistComponent } from './components/pricelist/pricelist.component';
import { MyPharmacyComponent } from './components/my-pharmacy/my-pharmacy.component';
import { MedicineDetailsComponent } from './components/medicine-details/medicine-details.component';
import { PromotionsComponent } from './components/promotions/promotions.component';
import { PharmacistsComponent } from './components/pharmacists/pharmacists.component';
import { RegisterPharmacyAdminComponent } from './components/register-pharmacy-admin/register-pharmacy-admin.component';

import { ListOfMedicinesComponent } from './components/list-of-medicines/list-of-medicines.component';
import { RegisterMedicineComponent } from './components/register-medicine/register-medicine.component';
import { ReserveMedPageComponent } from './components/reserve-med-page/reserve-med-page.component';
import { PharmacyMedicinesComponent } from './components/pharmacy-medicines/pharmacy-medicines.component';
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
import { MyAppointmentsComponent } from './components/my-appointments/my-appointments.component';
import { MakeAnAppDComponent } from './components/make-an-app-d/make-an-app-d.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { AddMedComponent } from './components/add-med/add-med.component';

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
  {path: 'my-appointments', component: MyAppointmentsComponent },
  {path: 'make-an-app-d',
      children : [
        {
          path: ':pid/:did',
          component: MakeAnAppDComponent
        }
      ]
   },
  {path: 'reserve-med-page',
      children : [
        {
          path: ':mid/:pid',
          component: ReserveMedPageComponent
        }
      ]

  },
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

  {path: 'register-dermatologist', component: RegisterDermatologistComponent},
  {path: 'pharmacy-medicines',
      children : [
        {
          path: ':id',
          component : PharmacyMedicinesComponent
        }
      ]
  },
  {path: 'register-medicine', component: RegisterMedicineComponent},
  {path: 'list-of-medicines', component: ListOfMedicinesComponent},
  {path: 'add-med',
      children : [
        {
          path: ':id',
          component : AddMedComponent
        }
      ]
  },
  {path: 'register-pharmacy-admin', component: RegisterPharmacyAdminComponent},
  {path : 'promotions',
    children : [
      {
        path: ':id',
        component: PromotionsComponent
      }
    ]
  },
  {path : 'medicine-details',
    children : [
      {
        path: ':id',
        component: MedicineDetailsComponent
      },
      {
        path: ':id/:pid',
        component: MedicineDetailsComponent
      }
    ]

  },
  {path : 'pharmacists', component: PharmacistsComponent},
  {path : 'my-pharmacy', component: MyPharmacyComponent },
  {path: 'pricelist', component: PricelistComponent},
  {path: 'complaint',
    children : [
      {
        path: ' ',
        component :ComplaintComponent
      },
      {
        path: ':id',
        component: ComplaintComponent
      }
    ]

  },
  {path: 'vacations', component: VacationsComponent},
  {path: 'register-sys-admin', component :RegisterSysAdminComponent},
  {path: 'register-supplier', component: RegisterSupplierComponent},
  {path: 'edit-medicine',
      children : [
        {
          path: ':mid/:pid',
          component: EditMedicineComponent
        }
      ]

  },
  {path: 'orders', component: OrdersComponent},
  {path: 'order-details',
      children : [
        {
          path: ':id',
          component: OrderDetailsComponent
        }
      ]
  },
  {path: 'statistic',
      children: [
        {
          path: ':id',
          component: StatisticComponent
        }
      ]

  },
  {path : 'loyalty-program', component : LoyaltyProgramComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [

];
