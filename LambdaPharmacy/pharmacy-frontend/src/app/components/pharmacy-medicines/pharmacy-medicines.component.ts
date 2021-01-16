import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { PharmacyService } from 'src/app/services/PharmacyService';
import { Router, ActivatedRoute } from '@angular/router';

import { Component, OnInit } from '@angular/core';
import { MedicineDTO } from 'src/app/model/MedicineDTO';
import { MedicineService } from 'src/app/services/medicineService';
import { AuthService } from 'src/app/services/AuthService';
import { UserDTO } from 'src/app/model/UserDTO';


@Component({
  selector: 'app-pharmacy-medicines',
  templateUrl: './pharmacy-medicines.component.html',
  styleUrls: ['./pharmacy-medicines.component.css']
})
export class PharmacyMedicinesComponent implements OnInit {

  loaded = false;
  fetchedMedicines: MedicinePreview[]= [];
  pharmacyID:number;
  profil : UserDTO;
  isPharmacyAdmin = false;
  isPatient = false;
  pharmacy : PharmacyDTO;
  anyLogged = false;

  constructor(
    private medicineService: MedicineService,
    private router: Router,
    private route :ActivatedRoute,
    private authService : AuthService,
    private pharmacyService: PharmacyService
  ) { }

  ngOnInit() {

    this.pharmacyID = this.route.snapshot.params.id;

    this.medicineService.getPharmacyMedicines(this.pharmacyID).subscribe(
      response => {
        this.fetchedMedicines = response;
        this.loaded = true;
      }
    );

     this.pharmacyService.getPharmacyById(this.pharmacyID).subscribe( response => {
       this.pharmacy = response;

     });

    this.authService.getLogged().subscribe( response => {
        this.profil = response;
        const role = this.profil.authorities[0];

        if(role == 'ROLE_PHARMACY_ADMIN'){
          this.isPharmacyAdmin = true;
          this.anyLogged = true;
        }
        else {
          this.isPharmacyAdmin = false;
        }
        if(role =='ROLE_PATIENT'){
          this.isPatient = true;
          this.anyLogged = true;
        } else {
          this.isPatient = false;
        }
        if(role == 'ROLE_SYS_ADMIN'){
          this.anyLogged= true;
        }
    });
  }

  showAddForm(){
    alert(this.pharmacyID);
    this.router.navigate(['/add-med/' + this.pharmacyID]);
  }

  backToPharmacy(id:number){
    this.router.navigate(['/pharmacy-details/' + id]);
  }

  showMedicineDetails(id:number, pid:number){
    alert(pid);
    this.router.navigate(['/medicine-details/' + id +'/'+ pid]);
  }

}
