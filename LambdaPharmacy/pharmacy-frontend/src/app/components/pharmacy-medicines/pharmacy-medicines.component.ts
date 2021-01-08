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
  fetchedMedicines: MedicineDTO[]= [];
  pharmacyID:number;
  profil : UserDTO;
  isPharmacyAdmin = false;

  constructor(
    private medicineService: MedicineService,
    private router: Router,
    private route :ActivatedRoute,
    private authService : AuthService
  ) { }

  ngOnInit() {

    this.pharmacyID = this.route.snapshot.params.id;

    this.medicineService.getPharmacyMedicines(this.pharmacyID).subscribe(
      response => {
        this.fetchedMedicines = response;
        this.loaded = true;
      }
    );

    this.authService.getLogged().subscribe( response => {
        this.profil = response;
        const role = this.profil.authorities[0];

        if(role == 'ROLE_PHARMACY_ADMIN'){
          this.isPharmacyAdmin = true;
        }
        else {
          this.isPharmacyAdmin = false;
        }
    });
  }

  showAddForm(){
    alert(this.pharmacyID);
    this.router.navigate(['/add-med/' + this.pharmacyID]);
  }

}
