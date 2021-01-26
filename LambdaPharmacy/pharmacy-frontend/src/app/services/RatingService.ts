import { PromotionDTO } from './../model/PromotionDTO';
import { Image } from './../model/Image';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject, throwError } from 'rxjs';
import {MedicineDTO} from '../model/MedicineDTO';
import {PharmacyDTO} from '../model/PharmacyDTO';
import { catchError, map } from 'rxjs/operators';

const ratingUrl = 'http://localhost:8051/rating';


@Injectable({
  providedIn: 'root'
})
export class RatingService {

  ratePharmacyUrl = ratingUrl + '/ratePharmacy/';
  rateUserUrl = ratingUrl + '/rateUser/';
  rateMedicineUrl = ratingUrl + '/rateMedicine/';
  changeRateUserUrl = ratingUrl + '/changeRateUser/';
  changeRatePharmacyUrl = ratingUrl + '/changeRatePharmacy/';



  constructor(private http: HttpClient, private router: Router) {}

  ratePharmacy(id:number, rate:number){

    return this.http.post(this.ratePharmacyUrl+id +'/'+rate, {

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

  rateUser(id:number, rate:number){

    return this.http.post(this.rateUserUrl+id +'/'+rate, {

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

  rateMedicine(id:number, rate:number){

    return this.http.post(this.rateMedicineUrl+id +'/'+rate, {

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

  changeRateUser(id:number, rate:number){

    return this.http.post(this.changeRateUserUrl+id +'/'+rate, {

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

  changeRatePharmacy(id:number, rate:number){

    return this.http.post(this.changeRatePharmacyUrl+id +'/'+rate, {

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
