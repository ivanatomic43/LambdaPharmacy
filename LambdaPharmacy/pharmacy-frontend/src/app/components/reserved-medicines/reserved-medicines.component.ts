import { MedicineService } from './../../services/medicineService';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { ReservationPreview } from 'src/app/model/ReservationPreview';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reserved-medicines',
  templateUrl: './reserved-medicines.component.html',
  styleUrls: ['./reserved-medicines.component.css']
})
export class ReservedMedicinesComponent implements OnInit {

  dataSource2 : MatTableDataSource<ReservationPreview>;
  reservations: ReservationPreview[] = [];
  displayedColumns2: string[]= [
    'id',
    'date',
    'medicineName',
    'pharmacyName',
    'action'
  ];


  constructor(
    private router: Router,
    private medicineService: MedicineService
  ) { }

  ngOnInit() {

    this.fetchReservations();


  }


  fetchReservations(){

    this.medicineService.getPatientReservations().subscribe(
      response => {
        this.reservations= response;
        this.dataSource2 = new MatTableDataSource(this.reservations);
      }, error => {
        if(error.status == 404){
          console.log("There is no reservations for this patient...");
        }
      }
    );


  }

}
