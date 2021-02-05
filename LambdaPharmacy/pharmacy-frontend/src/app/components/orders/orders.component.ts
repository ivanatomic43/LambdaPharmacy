import { Offer } from './../../model/Offer';

import { AuthService } from './../../services/AuthService';
import { MatTableDataSource } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { PurchaseOrderPreview } from 'src/app/model/PurchaseOrderPreview';
import { OrderService } from 'src/app/services/OrderService';
import { UserDTO } from 'src/app/model/UserDTO';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {


  //user part
  isPharmacyAdmin = false;
  isSupplier= false;
  loggedAdminID: number;
  profil: UserDTO;


  //orders- admin part
  dataSource : MatTableDataSource<PurchaseOrderPreview>;
  fetchedOrders: PurchaseOrderPreview[] = [];
  displayedColumns: string []= [
    'id',
    'date',
    'administrator',
    'status',
    'action'
  ];

  //orders - supplier part
  dataSource1 : MatTableDataSource<PurchaseOrderPreview>
  fetchedOrders1: PurchaseOrderPreview[] = [];
  displayedColumns1 : string [] = [
    'id',
    'date',
    'administrator',
    'pharmacy',
    'status',
    'action'
  ];

  //giving offer
  giveOfferForm: FormGroup;
  clickedOffer= false;
  orderID:number;
  totalPrice: number;
  deliveryTime:any;
  offer : Offer;




  constructor(
    private orderService: OrderService,
    private authService : AuthService,
    private formBuilder : FormBuilder

  ) { }

  ngOnInit() {

    this.authService.getLogged().subscribe(response => {
      this.profil= response;
      const role = this.profil.authorities[0];
      if(role == 'ROLE_PHARMACY_ADMIN'){
        this.isPharmacyAdmin = true;
        this.fetchOrders();

      } else {
        this.isPharmacyAdmin = false;
      }
      if(role == 'ROLE_SUPPLIER'){
        this.isSupplier= true;
        this.fetchOrdersSupplier();

      } else {
        this.isSupplier = false;
      }

  });

    this.giveOfferForm = this.formBuilder.group({
      orderID : [{value: '', disabled : true}, Validators.required],
      totalPrice : ['', Validators.required],
      deliveryTime: ['',Validators.required]


    });

  }


  fetchOrders(){

    this.orderService.getAllOrders().subscribe(response =>{
      this.fetchedOrders= response;
      this.dataSource = new MatTableDataSource(this.fetchedOrders);

    });

  }

  fetchOrdersSupplier(){
      this.orderService.getAllOrders().subscribe(response => {
        this.fetchedOrders1 = response;
        this.dataSource1 = new MatTableDataSource(this.fetchedOrders1);
      });
  }

  show(id:number){
    this.orderID= id;
    this.clickedOffer= true;
  }

  cancelOffer(){
    this.clickedOffer= false;
  }

  giveOffer(){

    this.offer = new Offer(
      this.giveOfferForm.get('orderID').value,
      this.giveOfferForm.get('totalPrice').value,
      this.giveOfferForm.get('deliveryTime').value
    );


    this.orderService.giveOffer(this.offer).subscribe(response => {
      alert("Offer sent!");
      this.fetchOrdersSupplier();
    });

  }

}
