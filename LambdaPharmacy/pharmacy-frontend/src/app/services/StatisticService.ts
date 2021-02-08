
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject, throwError } from 'rxjs';


const statisticUrl = 'http://localhost:8051/statistic';


@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  appointmentsUrl = statisticUrl + '/getAppointmentStatistic/';
  medicinesUrl = statisticUrl + '/getMedicinesStatistic/';


  constructor(private http: HttpClient, private router: Router) {}


  appointments(id:number){
    return this.http.get<any>(this.appointmentsUrl + id);
  }

  medicines(id:number){
    return this.http.get<any>(this.medicinesUrl + id);
  }










}
