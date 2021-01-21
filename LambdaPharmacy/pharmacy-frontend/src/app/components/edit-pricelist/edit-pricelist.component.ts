import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { MedicineService } from 'src/app/services/medicineService';


@Component({
  selector: 'app-edit-pricelist',
  templateUrl: './edit-pricelist.component.html',
  styleUrls: ['./edit-pricelist.component.css']
})
export class EditPricelistComponent implements OnInit {

  editPriceForm : FormGroup;

  constructor(
    public dialogRef : MatDialogRef<EditPricelistComponent>,
    public medicineService : MedicineService
  ) { }

  ngOnInit() {


  }


  closeDialog(){
    this.dialogRef.close();
  }

}
