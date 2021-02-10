import { DermDTO } from 'src/app/model/DermDTO';
import { NewCounceling } from './../model/NewCounceling';
import { SearchPharmacistParams } from './../model/SearchPharmacistParams';
import { NewPharmacist } from './../model/NewPharmacist';
import { AddingStaffDTO } from '../model/AddingStaffDTO';
import { RegistrationParams } from './../model/registrationParams';
import { RegisterDermatologistComponent } from './../components/register-dermatologist/register-dermatologist.component';
import {Injectable} from '@angular/core';
import {ApiService} from './ApiService';
import {ConfigService} from './ConfigService';
import {catchError, first, map} from 'rxjs/operators';

import {HttpClient} from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';

const pharmacistUrl = 'http://localhost:8051/pharmacist';

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  refreshPharmacists = new Subject<DermDTO[]>();
  getAll = pharmacistUrl + '/getAllPharmacists';
 registerPharmacistUrl = pharmacistUrl + '/registerPharmacist';
  addPharmacistUrl = pharmacistUrl + '/addPharmacist/';
  getAllPharm = pharmacistUrl + '/getAllPharmacistForPharmacy/';
  seePharmacistsUrl = pharmacistUrl + '/seePharmacists/';
  removePharmacistUrl = pharmacistUrl + '/removePharmacist/';
  getAllPUrl = pharmacistUrl + '/getAllP/';
  getAllPatientPharmacistsUrl = pharmacistUrl + '/getAllPatientPharmacists';



  constructor(
    private http: HttpClient,
    private apiService :ApiService,
    private configService : ConfigService
  ){}


  getAllPharmacists(){

    return this.http.post(this.getAll, {

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

  getAllPatientPharmacists(){

    return this.http.post(this.getAllPatientPharmacistsUrl, {

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

  getAllP(id:number){

    return this.http.post(this.getAllPUrl + id, {

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

  addPharmacist(pharmData : NewPharmacist, id:number){

    return this.http.post(this.addPharmacistUrl + id, pharmData);


  }

  getAllPharmacistForPharmacy(id:number){

    return this.http.post(this.getAllPharm + id, {

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




  seePharmacists(id:number, searchParams :SearchPharmacistParams){

    return this.http.post(this.seePharmacistsUrl+ id, searchParams, {

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

  removePharmacist(pid: number, did:number){
    return this.http.put(this.removePharmacistUrl + pid + '/' + did, {

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
