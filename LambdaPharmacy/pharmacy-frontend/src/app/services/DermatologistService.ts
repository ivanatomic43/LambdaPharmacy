import { DermDTO } from 'src/app/model/DermDTO';
import { AddingStaffDTO } from '../model/AddingStaffDTO';
import { RegistrationParams } from './../model/registrationParams';
import { RegisterDermatologistComponent } from './../components/register-dermatologist/register-dermatologist.component';
import {Injectable} from '@angular/core';
import {ApiService} from './ApiService';
import {ConfigService} from './ConfigService';
import {catchError, first, map} from 'rxjs/operators';

import {HttpClient} from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';

const dermatologistUrl = 'http://localhost:8051/dermatologist';

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  refreshDermatologists = new Subject<DermDTO[]>();
  getAllDermatologistsUrl = dermatologistUrl + '/getAllDermatologists'; //for select
  registerDermatologistUrl = dermatologistUrl + '/registerDermatologist';
  addDermatologistUrl = dermatologistUrl + '/addDermatologist/';
  getAllDerm = dermatologistUrl + '/getAllDermatologistForPharmacy/' //for each pharmacy
  removeDermatologistUrl = dermatologistUrl + '/removeDermatologist/';



  getDermatologistsUrl = dermatologistUrl + '/getDermatologists';

  constructor(
    private http: HttpClient,
    private apiService :ApiService,
    private configService : ConfigService
  ){}


  getAllDermatologists(){ //for select

    return this.http.post(this.getAllDermatologistsUrl, {

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

  getDermatologists(){ //for pharmacy admin & patient

    return this.http.post(this.getDermatologistsUrl, {

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

  registerDermatologist(dermatologistParams : RegistrationParams){

    return this.http.post(this.registerDermatologistUrl, dermatologistParams, {

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

  addDermatologist(dermData : AddingStaffDTO, id:number){

    return this.http.post(this.addDermatologistUrl + id, dermData);


  }

  getAllDermatologistsForPharmacy(id:number){

    return this.http.post(this.getAllDerm + id, {

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

  removeDermatologist(pid: number, did:number){
    return this.http.put(this.removeDermatologistUrl + pid + '/' + did, {

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
