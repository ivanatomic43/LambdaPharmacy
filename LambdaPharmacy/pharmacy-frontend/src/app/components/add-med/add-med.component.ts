import { OrderMedicine } from './../../model/OrderMedicine';
import { MatTableDataSource } from '@angular/material';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { MedicineService } from 'src/app/services/medicineService';

@Component({
  selector: 'app-add-med',
  templateUrl: './add-med.component.html',
  styleUrls: ['./add-med.component.css']
})
export class AddMedComponent implements OnInit {

  fetchedMedicines: MedicinePreview[] = [];
  pharmacyID: number;
  medicineID: number;
  medicineForm: FormGroup;

  medicineSource : MatTableDataSource<OrderMedicine>;
  medicineData : OrderMedicine[] = [];
  columnMedicine: string[] = ['medicine'];

  respData: OrderMedicine[] = [];


  constructor(
    private router: Router,
    private medicineService: MedicineService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {

      this.pharmacyID = this.route.snapshot.params.id;

      this.medicineService.getMedForAdd(this.pharmacyID).subscribe( response => {
        this.fetchedMedicines = response;

      });

      this.medicineForm= new FormGroup({
        medicine: new FormControl('', [Validators.required]),
        addQuantity: new FormControl('',[Validators.required])
      });


  }


  onSubmitMedicine(){
    const medicine = this.medicineForm.getRawValue().medicine;
    const medicineData = { name: medicine};
    this.medicineSource = new MatTableDataSource(this.medicineData);

  }

  fetchData(){



  }

}
