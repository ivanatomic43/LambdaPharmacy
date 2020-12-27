import { NewAppointmentDTO } from './../model/NewAppointmentDTO';
import {Injectable} from '@angular/core';
import {ApiService} from './ApiService';
import {ConfigService} from './ConfigService';
import {catchError, first, map} from 'rxjs/operators';

import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';


const apiUrl = 'http://localhost:8051/api';
const authUrl = 'http://localhost:8051/auth';
const appointmentUrl = 'http://localhost:8051/appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {


  getAllAppointmentsUrl = appointmentUrl + '/getAllAppointments';  //history of appointments
  getAllPredefined = appointmentUrl + '/getAllPredefined/'; //for each pharmacy
  createAppointmentUrl = appointmentUrl + '/createAppointment';
  reserveAppointmentUrl = appointmentUrl + '/reserveAppointment/';

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient
  ) {
  }

  getAllAppointments() {
    return this.http.post(this.getAllAppointmentsUrl ,{

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


  createAppointment(newApp: NewAppointmentDTO){
        return this.http.post<any>(this.createAppointmentUrl, newApp);
  }

  getAllPredefinedAp(id:number) {
    return this.http.post<any>(this.getAllPredefined + id, {

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

  reserveAppointment(id:number){
    return this.http.post<any>(this.reserveAppointmentUrl + id, {

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
