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


  getAllAppointmentsUrl = appointmentUrl + '/getAllAppointments';

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient
  ) {
  }

  getAllAppointments() {
    return this.http.post(this.getAllAppointmentsUrl, {

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
