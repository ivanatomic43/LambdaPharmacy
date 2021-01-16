import { PromotionDTO } from './../model/PromotionDTO';
import { Image } from './../model/Image';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject, throwError } from 'rxjs';
import {MedicineDTO} from '../model/MedicineDTO';
import {PharmacyDTO} from '../model/PharmacyDTO';
import { catchError, map } from 'rxjs/operators';


const pharmacyUrl = 'http://localhost:8051/pharmacy';
const pharmacyAdminUrl = 'http://localhost:8051/pharmacyAdmin';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  refreshPharmacies = new Subject<PharmacyDTO[]>();
  allPharmaciesUrl = pharmacyUrl + '/getAllPharmacies';
  createPharmacyUrl = pharmacyUrl + '/createPharmacy';
  getPharmacyUrl = pharmacyUrl + '/getPharmacy/';
  getAdministratorsUrl = pharmacyUrl+ '/getAdministrators';
  subscribeUrl = pharmacyUrl + '/subscribe/';
  getSubPharmaciesUrl = pharmacyUrl + '/getSubPharmacies';
  fetchAllPromotionsUrl = pharmacyUrl + '/fetchAllPromotions/';
  createPromotionUrl = pharmacyUrl + '/createPromotion/';



  constructor(private http: HttpClient, private router: Router) {}

  allPharmacies() {
    return this.http.get<any>(this.allPharmaciesUrl);
  }

  createNewPharmacy(newPharmacy : PharmacyDTO){
      return this.http.post<any>(this.createPharmacyUrl, newPharmacy,{headers: {"Content-Type":"application/json"}});
  }

  addImage(id: number, image: Image){
        let data = new FormData();
        data.append("image", image.data);
        console.log("SLIKA U ADD:" + image.data);

        return this.http.post('http://localhost:8051/pharmacy/addImages/'+ id, data);
  }

  images(id:number){
    return this.http.get<any>('http://localhost:8051/pharmacy/getImage/' + id);
  }

  getPharmacyById(id: number){
    return this.http.get<any>(this.getPharmacyUrl + id);
  }

  getAdministrators(){

    return this.http.post(this.getAdministratorsUrl, {

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

  subscribeForNewsletter(id: number){

    return this.http.post(this.subscribeUrl + id, {

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


  getSubPharmacies(){

    return this.http.post(this.getSubPharmaciesUrl, {

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

  fetchAllPromotions(id:number){

    return this.http.post(this.fetchAllPromotionsUrl + id, {

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

  createPromotion(id: number, promotion : PromotionDTO){

    return this.http.post(this.createPromotionUrl+id, promotion, {

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
