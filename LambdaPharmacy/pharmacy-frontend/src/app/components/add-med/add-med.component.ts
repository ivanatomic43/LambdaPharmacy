import { PurchaseOrder } from './../../model/PurchaseOrder';

import { OrderMedicine } from './../../model/OrderMedicine';
import { MatTableDataSource } from '@angular/material';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { MedicineService } from 'src/app/services/medicineService';
import { MedicineDTO } from 'src/app/model/MedicineDTO';

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

  dataSource : MatTableDataSource<OrderMedicine>;
  medicineData : OrderMedicine[] = [];
  displayedColumns : string[] = [
    'id',
    'quantity'
  ];



  medID: number;
  medicineName : string;
  quantity: number;

  item: OrderMedicine;
  itemList: Array<OrderMedicine> = [];

  orderForm : FormGroup;
  orderDate: any;
  purchaseOrder : PurchaseOrder;






  constructor(
    private router: Router,
    private medicineService: MedicineService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
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

      this.orderForm = new FormGroup({
        orderDate: new FormControl('', [Validators.required])
      });


  }

  addToList(){

     this.item = new OrderMedicine(
       this.medicineForm.get('medicine').value,
       this.medicineForm.get('addQuantity').value
     );

     this.itemList.push(this.item);
      this.dataSource = new MatTableDataSource(this.itemList);


  }


  orderMedicine(){

    this.orderDate = this.orderForm.get('orderDate').value;
    console.log(this.itemList);

    this.purchaseOrder = new PurchaseOrder(
      this.itemList,
      this.orderDate
    );

    this.medicineService.orderMedicine(this.purchaseOrder, this.pharmacyID).subscribe(response =>{
      alert("Order sent!");
      this.router.navigate(['/pharmacy-details/'+ this.pharmacyID]);
      this.itemList= [];
    });


  }



}
