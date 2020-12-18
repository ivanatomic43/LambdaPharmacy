import { Image } from './../model/Image';
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
  createPharmacyUrl = pharmacyUrl + '/createPharmacy';
  getPharmacyUrl = pharmacyUrl + '/getPharmacy/'



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
}
