
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject, throwError } from 'rxjs';

import { catchError, map } from 'rxjs/operators';
import { Offer } from '../model/Offer';

const orderUrl = 'http://localhost:8051/order';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  getAllOrdersUrl = orderUrl + '/getAllOrders';
  giveOfferUrl = orderUrl + '/giveOffer';
  getOrderDetailsUrl = orderUrl + '/getOrderDetails/';
  getOrdersMedicinesUrl = orderUrl + '/getOrdersMedicines/';
  getOffersUrl = orderUrl + '/getOffers/';
  acceptOfferUrl = orderUrl + '/acceptOffer/';




  constructor(private http: HttpClient, private router: Router) {}

  getAllOrders(){

    return this.http.get<any>(this.getAllOrdersUrl);
  }

  giveOffer(offer:Offer){
    return this.http.post<any>(this.giveOfferUrl, offer);
  }

  getOrderDetails(id:number){
    return this.http.get<any>(this.getOrderDetailsUrl + id);
  }

  getOrdersMedicines(id:number){
    return this.http.get<any>(this.getOrdersMedicinesUrl+ id);
  }

  getOffers(id:number){
      return this.http.get<any>(this.getOffersUrl + id);
  }

  acceptOffer(id:number){
    return this.http.get(this.acceptOfferUrl +id);
  }



}
