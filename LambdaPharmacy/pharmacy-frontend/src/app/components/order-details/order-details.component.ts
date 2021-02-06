import { OfferPreview } from './../../model/OfferPreview';
import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { MatTableDataSource } from '@angular/material';
import { PurchaseOrderPreview } from './../../model/PurchaseOrderPreview';
import { PurchaseOrder } from './../../model/PurchaseOrder';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/OrderService';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orderID: number;
  fetchedOrder : PurchaseOrderPreview;

  dataSource: MatTableDataSource<MedicinePreview>;
  fetchedMedicines : MedicinePreview[] = [];
  displayedColumns: string[]= [
    'id',
    'name',
    'quantity'
  ];

  dataSource1: MatTableDataSource<OfferPreview>;
  fetchedOffers : OfferPreview[] = [];
  displayedColumns1 : string[] = [
    'id',
    'supplier',
    'totalPrice',
    'deliveryTime',
    'status',
    'action'
  ];

  constructor(
    private route:ActivatedRoute,
    private router:Router,
    private orderService: OrderService
  ) { }

  ngOnInit() {

    this.orderID = this.route.snapshot.params.id ;

    this.orderService.getOrderDetails(this.orderID).subscribe(response =>{
        this.fetchedOrder= response;

    });

    this.orderService.getOrdersMedicines(this.orderID).subscribe(response => {
      this.fetchedMedicines= response;
      this.dataSource = new MatTableDataSource(this.fetchedMedicines);
    });

    this.getOffers();

  }

  accept(id:number, status:string){

    if(status === 'ACCEPTED' || status ==='DECLINED'){
        alert("You already accepted/declined this offer!");
        return;
    }

    this.orderService.acceptOffer(id).subscribe(response => {
      alert("Offer accepted!");
      this.getOffers();

    }, error => {
      if(error.status == 400){
        alert("The offer can not be accepted because you did not make the order...")
        return;
      }
    });
  }

  getOffers(){
    this.orderService.getOffers(this.orderID).subscribe(response =>{
      this.fetchedOffers = response;
      this.dataSource1 = new MatTableDataSource(this.fetchedOffers);
    });
  }

}
