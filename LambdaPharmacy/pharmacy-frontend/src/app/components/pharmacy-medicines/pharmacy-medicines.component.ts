import { Router, ActivatedRoute } from '@angular/router';

import { Component, OnInit } from '@angular/core';
import { MedicineDTO } from 'src/app/model/MedicineDTO';
import { MedicineService } from 'src/app/services/medicineService';


@Component({
  selector: 'app-pharmacy-medicines',
  templateUrl: './pharmacy-medicines.component.html',
  styleUrls: ['./pharmacy-medicines.component.css']
})
export class PharmacyMedicinesComponent implements OnInit {

  loaded = false;
  fetchedMedicines: MedicineDTO[]= [];
  medicineID:number;

  constructor(
    private medicineService: MedicineService,
    private router: Router,
    private route :ActivatedRoute
  ) { }

  ngOnInit() {

    this.medicineID = this.route.snapshot.params.id;

    this.medicineService.getPharmacyMedicines(this.medicineID).subscribe(
      response => {
        this.fetchedMedicines = response;
        this.loaded = true;
      }
    );
  }

}
