import { RegistrationParams } from './../model/registrationParams';
import { RegisterDermatologistComponent } from './../components/register-dermatologist/register-dermatologist.component';
import {Injectable} from '@angular/core';
import {ApiService} from './ApiService';
import {ConfigService} from './ConfigService';
import {catchError, first, map} from 'rxjs/operators';

import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';

const dermatologistUrl = 'http://localhost:8051/dermatologist';

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  getAll = dermatologistUrl + '/getAllDermatologists';
  registerDermatologistUrl = dermatologistUrl + '/registerDermatologist';


  constructor(
    private http: HttpClient,
    private apiService :ApiService,
    private configService : ConfigService
  ){}


  getAllDermatologists(){

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


}
