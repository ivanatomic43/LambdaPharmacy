import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import {MedicineDTO} from '../model/MedicineDTO';
import {PharmacyDTO} from '../model/PharmacyDTO';

const pharmacyUrl = 'http://localhost:8051/pharmacy';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  refreshPharmacies = new Subject<PharmacyDTO[]>();
  allPharmaciesUrl = pharmacyUrl + '/getAllPharmacies';


  constructor(private http: HttpClient, private router: Router) {}

  allMedicines() {
    return this.http.get<any>(this.allPharmaciesUrl);
  }


}
