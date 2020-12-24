import { NewPharmacist } from './../model/NewPharmacist';
import { AddingStaffDTO } from '../model/AddingStaffDTO';
import { RegistrationParams } from './../model/registrationParams';
import { RegisterDermatologistComponent } from './../components/register-dermatologist/register-dermatologist.component';
import {Injectable} from '@angular/core';
import {ApiService} from './ApiService';
import {ConfigService} from './ConfigService';
import {catchError, first, map} from 'rxjs/operators';

import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';

const pharmacistUrl = 'http://localhost:8051/pharmacist';

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  getAll = pharmacistUrl + '/getAllPharmacists';
 registerPharmacistUrl = pharmacistUrl + '/registerPharmacist';
  addPharmacistUrl = pharmacistUrl + '/addPharmacist/';
  getAllPharm = pharmacistUrl + '/getAllPharmacistForPharmacy/';


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

}
