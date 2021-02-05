
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subject, throwError } from 'rxjs';

import { catchError, map } from 'rxjs/operators';

const orderUrl = 'http://localhost:8051/order';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  getAllOrdersUrl = orderUrl + '/getAllOrders/';



  constructor(private http: HttpClient, private router: Router) {}

  getAllOrders(){

    return this.http.get<any>(this.getAllOrdersUrl);
  }


}
