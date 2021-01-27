import { EditPrice } from './../model/EditPrice';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  getNotReservedMedicinesUrl = medicineUrl + '/getNotReservedMedicines';
  cancelMedicineReservationUrl = medicineUrl + '/cancelMedicineReservation/';
  getMedicineDetailsUrl = medicineUrl + '/getMedicineDetails/';
  getPharmacyMedicinesPricelistUrl = medicineUrl + '/getPharmacyMedicinesPricelist';
  editPriceUrl = medicineUrl + '/editPrice/';
  pickUpMedicineUrl = medicineUrl +'/pickUpMedicine/';
  getAllPickedUrl = medicineUrl + '/getPickedUpMedicines';


  constructor(private http: HttpClient, private router: Router) {}


  editPriceForm: FormGroup = new FormGroup({


    name: new FormControl({value: '', disabled:true}, Validators.required),

    price: new FormControl('', Validators.required),
    priceFrom: new FormControl(''),
    priceTo: new FormControl(''),



  });

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

  //front page
  getAllMedicinesInSystem(){
    return this.http.get<any>(this.getAllMedicinesInSystemUrl);
  }

  //for patient role
  getNotReservedMedicines(){
    return this.http.get<any>(this.getNotReservedMedicinesUrl);
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

  cancelMedicineReservation(id:number){

    return this.http.post(this.cancelMedicineReservationUrl + id, {

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

  getMedicineDetails(medicineID: number){

    return this.http.get(this.getMedicineDetailsUrl + medicineID, {

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

  getPharmacyMedicinesPricelist(){
    return this.http.get<any>(this.getPharmacyMedicinesPricelistUrl);
  }
/*
  populateForm(name: string, price:any, priceFrom: any, priceTo: any){

      this.editPrice = new EditPrice(name, price, priceFrom, priceTo);
    //console.log(element);
       this.editPriceForm.setValue(this.editPrice);

  }
*/
  addMedicine(medicineData:any){
    return null;
  }

  editPrice(editPriceModel :EditPrice, pharmacyID: number){

    return this.http.post<any>(this.editPriceUrl+ pharmacyID, editPriceModel);

  }

  pickUpMedicine(id:number, pharmacyID: number){

    return this.http.get(this.pickUpMedicineUrl + id+'/'+pharmacyID, {

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

  getAllPicked(){

    return this.http.post(this.getAllPickedUrl, {

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
