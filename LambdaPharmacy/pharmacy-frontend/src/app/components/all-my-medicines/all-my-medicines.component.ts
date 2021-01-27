import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { MatTableDataSource } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { RatingService } from 'src/app/services/RatingService';
import { MedicineService } from 'src/app/services/medicineService';

@Component({
  selector: 'app-all-my-medicines',
  templateUrl: './all-my-medicines.component.html',
  styleUrls: ['./all-my-medicines.component.css']
})
export class AllMyMedicinesComponent implements OnInit {

  dataSource: MatTableDataSource<MedicinePreview>;
  fetchedMedicines: MedicinePreview[] = [];
  displayedColumns: string [] = [
    'id',
  'code',
  'name',
  'rating',
  'myRate',
  'action'
  ];

  clicked = false;
  clickedChange = false;
  rateMedicineID : number;
  rateName : string;
  rate: any;
  rateForm: FormGroup;

  constructor(
    private ratingService : RatingService,
    private medicineService : MedicineService,
    private formBuilder : FormBuilder

  ) { }

  ngOnInit() {

    this.rateForm = this.formBuilder.group({
      rateMedicineID : [{value: '', disabled: true}, Validators.required],
      rateName: [{value: '', disabled: true}, Validators.required],
      rate: ['', Validators.required]
    });

    this.fetchMedicines();

  }

  fetchMedicines(){

    this.medicineService.getAllPicked().subscribe(response => {
      this.fetchedMedicines = response;
      this.dataSource = new MatTableDataSource(this.fetchedMedicines);
    });

  }

  rateMedicineClick(id: number, name: string){
    this.clicked = true;
     this.rateMedicineID = id;
     this.rateName = name;
  }

  changeRateMedicineClick(id: number, name: string){
    this.clicked = true;
    this.clickedChange = true;
    this.rateMedicineID = id;
    this.rateName = name;
  }

  cancelMedicineRate(){
      this.clicked = false;
  }

  rateMedicine(){
    this.rateMedicineID = this.rateForm.get('rateMedicineID').value;
    this.rate = this.rateForm.get('rate').value;


    this.ratingService.rateMedicine(this.rateMedicineID, this.rate).subscribe(response=>{
      alert("Medicine rated!");
      this.clicked= false;
      this.fetchMedicines();
    });
  }

  changeRateMedicine(){
    this.rateMedicineID = this.rateForm.get('rateMedicineID').value;
    this.rate = this.rateForm.get('rate').value;


    this.ratingService.changeRateMedicine(this.rateMedicineID, this.rate).subscribe(response=>{
      alert("Medicine rate changed!");
      this.clicked= false;
      this.fetchMedicines();
    });

  }
}
