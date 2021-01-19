import { SearchPharmacistParams } from './../model/SearchPharmacistParams';
import { MedicineSearch } from './../model/MedicineSearch';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SimpleSearch } from 'src/app/model/SimpleSearch';
import { Subject, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { MedicineService } from './medicineService';
import { stringify } from 'querystring';
import { NumberValueAccessor } from '@angular/forms/src/directives';
import { catchError, map } from 'rxjs/operators';


const searchUrl = 'http://localhost:8051/search';

@Injectable({providedIn: 'root'})
export class SearchService {

  private searchPharmacyUrl = searchUrl + '/searchPharmacy';
  private searchMedicineUrl = searchUrl + '/searchMedicine/';
  private searchPharmacistUrl = searchUrl + '/searchPharmacist';
  searchPharmByParamsUrl = searchUrl + '/searchPharmByParams/';
  searchDermByParamsUrl = searchUrl + '/searchDermByParams/';


  name: string;
  location: string;
  rating: number;

  constructor(

    private http: HttpClient,
    private router: Router,

  ){}

    searchPharmacy(data: SimpleSearch){

        return this.http.post<any>(this.searchPharmacyUrl, data);
    }

    searchMedicine(name :string){

        return this.http.get<any>(this.searchMedicineUrl + name);
    }

    searchPharmacist(searchParams : SearchPharmacistParams){

      return this.http.post<any>(this.searchPharmacistUrl, searchParams);
    }

    searchPharmByParams(name:string, surname:string){

      return this.http.post(this.searchPharmByParamsUrl + name + '/' + surname, {

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


    searchDermByParams(name:string, surname:string){

      return this.http.post(this.searchDermByParamsUrl + name + '/' + surname, {

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




