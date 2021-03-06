


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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TokenInterceptor} from './interceptor/TokenInterceptor';
import { PatientProfileComponent } from './components/patient-profile/patient-profile.component';
import { AlertComponent } from './alert/alert.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { PasswordChangeComponent } from './components/password-change/password-change.component';
import { ReservedMedicinesComponent } from './components/reserved-medicines/reserved-medicines.component';
import { AllMyMedicinesComponent } from './components/all-my-medicines/all-my-medicines.component';
import { MyAppointmentsComponent } from './components/my-appointments/my-appointments.component';
import { MakeAnAppDComponent } from './components/make-an-app-d/make-an-app-d.component';
import { MakeAnAppPComponent } from './components/make-an-app-p/make-an-app-p.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { HistoryOfAppDComponent } from './components/history-of-app-d/history-of-app-d.component';
import { HistoryOfAppPComponent } from './components/history-of-app-p/history-of-app-p.component';
import { SubPharmaciesComponent } from './components/sub-pharmacies/sub-pharmacies.component';
import {SortPipe} from "./services/SortPipe";
import { MatTableModule } from "@angular/material/table";
import {MatSortModule} from "@angular/material";
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
import { PharmacyMedicinesComponent } from './components/pharmacy-medicines/pharmacy-medicines.component';
import { ReserveMedPageComponent } from './components/reserve-med-page/reserve-med-page.component';
import { RegisterMedicineComponent } from './components/register-medicine/register-medicine.component';

import { AddMedComponent } from './components/add-med/add-med.component';
import { RegisterPharmacyAdminComponent } from './components/register-pharmacy-admin/register-pharmacy-admin.component';
import { PharmacistsComponent } from './components/pharmacists/pharmacists.component';
import { PromotionsComponent } from './components/promotions/promotions.component';
import { MedicineDetailsComponent } from './components/medicine-details/medicine-details.component';
import { MyPharmacyComponent } from './components/my-pharmacy/my-pharmacy.component';
import { PricelistComponent } from './components/pricelist/pricelist.component';
import { EditPricelistComponent } from './components/edit-pricelist/edit-pricelist.component';
import { MaterialModule } from './material/material.modul';
import { MatInputModule, MatFormFieldModule } from '@angular/material';
import { VacationsComponent } from './components/vacations/vacations.component';
import { RegisterSysAdminComponent } from './components/register-sys-admin/register-sys-admin.component';
import { RegisterSupplierComponent } from './components/register-supplier/register-supplier.component';
import { EditMedicineComponent } from './components/edit-medicine/edit-medicine.component';
import { AgmCoreModule} from '@agm/core';
import { OrdersComponent } from './components/orders/orders.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { StatisticComponent } from './components/statistic/statistic.component';
import { ChartsModule } from 'ng2-charts';
import { LoyaltyProgramComponent } from './components/loyalty-program/loyalty-program.component';






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
    PatientProfileComponent,
    AlertComponent,
    EditProfileComponent,
    PasswordChangeComponent,
    ReservedMedicinesComponent,
    AllMyMedicinesComponent,
    MyAppointmentsComponent,
    MakeAnAppDComponent,
    MakeAnAppPComponent,
    ComplaintComponent,
    HistoryOfAppDComponent,
    HistoryOfAppPComponent,
    SubPharmaciesComponent,
    SortPipe,
    DermatologistsComponent,
    ManagePharmacyComponent,
    ManageDermatologistComponent,
    NewPharmacyComponent,
    PharmacyDetailsComponent,
    RegisterDermatologistComponent,
    AddDermComponent,
    AddPharmComponent,
    PharmacyMedicinesComponent,
    ReserveMedPageComponent,
    RegisterMedicineComponent,
    AddMedComponent,
    RegisterPharmacyAdminComponent,
    PharmacistsComponent,
    PromotionsComponent,
    MedicineDetailsComponent,
    MyPharmacyComponent,
    PricelistComponent,
    EditPricelistComponent,
    VacationsComponent,
    RegisterSysAdminComponent,
    RegisterSupplierComponent,
    EditMedicineComponent,
    OrdersComponent,
    OrderDetailsComponent,
    StatisticComponent,
    LoyaltyProgramComponent


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
    NoopAnimationsModule,
    MatSortModule,
    MaterialModule,
    MatInputModule,
    MatFormFieldModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBLBXRgczQ3dIN3_ev-hJL0JqMPoQCXpio'
    }),
    ChartsModule
  ],
  entryComponents: [
    NewPharmacyComponent,
    EditPricelistComponent,
    ComplaintComponent
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
