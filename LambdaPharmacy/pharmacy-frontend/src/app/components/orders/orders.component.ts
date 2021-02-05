
import { AuthService } from './../../services/AuthService';
import { MatTableDataSource } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { PurchaseOrderPreview } from 'src/app/model/PurchaseOrderPreview';
import { OrderService } from 'src/app/services/OrderService';
import { UserDTO } from 'src/app/model/UserDTO';

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

  constructor(
    private orderService: OrderService,
    private authService : AuthService

  ) { }

  ngOnInit() {

    this.authService.getLogged().subscribe(response => {
      this.profil= response;
      const role = this.profil.authorities[0];
      if(role == 'ROLE_PHARMACY_ADMIN'){
        this.isPharmacyAdmin = true;
        this.loggedAdminID= this.profil.
      } else {
        this.isPharmacyAdmin = false;
      }
      if(role == 'ROLE_SUPPLIER'){
        this.isSupplier= true;
        //checking subscription


  });

    this.fetchOrders();

  }


  fetchOrders(){

    this.orderService.getAllOrders().subscribe(response =>{
      this.fetchedOrders= response;
      this.dataSource = new MatTableDataSource(this.fetchedOrders);

    });

  }

}
