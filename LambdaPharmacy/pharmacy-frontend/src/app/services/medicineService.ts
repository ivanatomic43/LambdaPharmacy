import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import {MedicineDTO} from '../model/MedicineDTO';

const medicineUrl = 'http://localhost:8051/medicine';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  refreshMedicines = new Subject<MedicineDTO[]>();
  allMedicinesUrl = medicineUrl + '/getAllMedicines';


  constructor(private http: HttpClient, private router: Router) {}

  allMedicines() {
    return this.http.get<any>(this.allMedicinesUrl);
  }


}
