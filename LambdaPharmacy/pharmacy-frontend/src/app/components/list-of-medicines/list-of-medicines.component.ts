import { Component, OnInit } from '@angular/core';
import {MedicineDTO} from '../../model/MedicineDTO';
import {MedicineService} from '../../services/medicineService';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-of-medicines',
  templateUrl: './list-of-medicines.component.html',
  styleUrls: ['./list-of-medicines.component.css']
})
export class ListOfMedicinesComponent implements OnInit {

  loaded = false;
  fetchedMedicines: MedicineDTO[] = [];

  constructor(
    private medicineService: MedicineService,
    private router: Router

  ) {}

  ngOnInit(){
    this.medicineService.allMedicines().subscribe(
      resp => {
        this.fetchedMedicines = resp;
        console.log(this.fetchedMedicines);
        this.loaded = true;

      },
      err => {
        console.log("greska u fethc");
      }
    );

    this.medicineService.refreshMedicines.subscribe(refreshMedicines => {
      this.fetchedMedicines = refreshMedicines;
    });


  }


}
