import { MedicineSearch } from './../model/MedicineSearch';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SimpleSearch } from 'src/app/model/SimpleSearch';
import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { MedicineService } from './medicineService';
import { stringify } from 'querystring';
import { NumberValueAccessor } from '@angular/forms/src/directives';


const searchUrl = 'http://localhost:8051/search';

@Injectable({providedIn: 'root'})
export class SearchService {

  private searchPharmacyUrl = searchUrl + '/searchPharmacy';
  private searchMedicineUrl = searchUrl + '/searchMedicine/';

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

}
