import { NewCounceling } from './../model/NewCounceling';
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
  getPatientAppointmentsUrl = appointmentUrl + '/getPatientAppointments'; //all types
  cancelAppointmentUrl = appointmentUrl + '/cancelAppointment/';
  reserveCouncelingUrl = appointmentUrl + '/reserveCounceling';
  endAppointmentUrl = appointmentUrl + '/endAppointment/';
  getVisitedDoctorsUrl = appointmentUrl + '/getVisitedDoctors'; //for rating
  getVisitedPharmaciesUrl = appointmentUrl + '/getVisitedPharmacies';
  getEndedAppointmentsUrl = appointmentUrl + '/getEndedAppointments';
  cancelCouncelingUrl = appointmentUrl + '/cancelCounceling/';


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

  reserveCounceling(newAppointment : NewCounceling){
    return this.http.post<any>(this.reserveCouncelingUrl, newAppointment, {

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


  getPatientAppointments() {
    return this.http.post<any>(this.getPatientAppointmentsUrl, {

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

  cancelAppointment(id:number) {
    return this.http.put<any>(this.cancelAppointmentUrl + id, {

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

  endAppointment(id:number){

    return this.http.post<any>(this.endAppointmentUrl + id, {

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

  getVisitedDoctors() {
    return this.http.post<any>(this.getVisitedDoctorsUrl, {

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

  getVisitedPharmacies() {
    return this.http.post<any>(this.getVisitedPharmaciesUrl, {

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

  getEndedAppointments() {
    return this.http.post<any>(this.getEndedAppointmentsUrl, {

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


  cancelCounceling(id:number) {
    return this.http.put<any>(this.cancelCouncelingUrl+ id, {

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
