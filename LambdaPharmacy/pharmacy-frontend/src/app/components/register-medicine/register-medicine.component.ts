import { MedicineDTO } from './../../model/MedicineDTO';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MedicineService } from 'src/app/services/medicineService';
import { NewMedicine } from 'src/app/model/NewMedicine';

@Component({
  selector: 'app-register-medicine',
  templateUrl: './register-medicine.component.html',
  styleUrls: ['./register-medicine.component.css']
})
export class RegisterMedicineComponent implements OnInit {

  medicineForm : FormGroup;
  medicineParams : NewMedicine;
  //modeOptions= ['NO_RECIPE','RECIPE'];

  medicine : NewMedicine = new NewMedicine();

  constructor(
    private medicineService: MedicineService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.medicineForm = this.formBuilder.group({
      medicineCode: ['', Validators.required],
      name: ['', Validators.required],
      medicineType: ['', Validators.required],
      shape: ['', Validators.required],
      structure: ['', Validators.required],
      producer: ['', Validators.required],
      medicineMode: ['', Validators.required],
      contraindications: ['', Validators.required],
      note: ['', Validators.required],
      dailyDose: ['', Validators.required]
    });


  }


  registerMedicine(){

    console.log(this.medicine);

    this.medicineService.registerMedicine(this.medicine).subscribe(
      response => {
        alert("Medicine registred!");
        this.router.navigate(['/list-of-medicines']);
      }
    );




  }

}
