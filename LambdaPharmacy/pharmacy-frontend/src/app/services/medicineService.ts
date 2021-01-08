import { NewMedicine } from './../model/NewMedicine';
import { ReservationParams } from './../model/ReservationParams';
import { MedicinePreview } from './../model/MedicinePreview';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import {MedicineDTO} from '../model/MedicineDTO';
import {catchError, first, map} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';

const medicineUrl = 'http://localhost:8051/medicine';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  refreshMedicines = new Subject<MedicinePreview[]>();
  allMedicinesUrl = medicineUrl + '/getAllMedicines';
  getPharmacyMedicinesUrl = medicineUrl + '/getPharmacyMedicines/';
  reserveMedicineUrl = medicineUrl + '/reserveMedicine';
  getPatientReservationsUrl = medicineUrl + '/getPatientReservations';
  getAllMedicinesInSystemUrl = medicineUrl + '/getAllMedicinesInSystem';
  registerMedicineUrl = medicineUrl + '/registerMedicine';
  getMedForAddUrl = medicineUrl + '/getMedForAdd/';
  addMedicineToPharmacyUrl = medicineUrl + '/addMedicineToPharmacy/';


  constructor(private http: HttpClient, private router: Router) {}

  allMedicines() {
    return this.http.get<any>(this.allMedicinesUrl);
  }

  getPharmacyMedicines(id:number){
    return this.http.get<any>(this.getPharmacyMedicinesUrl + id);
  }

  reserveMedicine(reservationParams: ReservationParams){
    return this.http.post<any>(this.reserveMedicineUrl, reservationParams);
  }

  getPatientReservations(){
    return this.http.get<any>(this.getPatientReservationsUrl);
  }

  getAllMedicinesInSystem(){
    return this.http.get<any>(this.getAllMedicinesInSystemUrl);
  }

  registerMedicine(medicineParams: NewMedicine){
    return this.http.post<any>(this.registerMedicineUrl, medicineParams);
  }

  getMedForAdd(pharmacyID:number){

    return this.http.post(this.getMedForAddUrl + pharmacyID, {

    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );

  }




  addMedicineToPharmacy(pharmacyID:number, medicineID: number){

    return this.http.post(this.addMedicineToPharmacyUrl + pharmacyID + '/' + medicineID, {

    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );

  }
}
